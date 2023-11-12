package org.example.blabla.security;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.domain.user.UserService;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.AuthResponseStatus;
import org.example.blabla.model.ConfirmAuthResponse;
import org.example.blabla.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthSmsSender authSmsSender;
    private final UserService userService;

    private static final ConfirmAuthResponse SUCCESS_NEW = new ConfirmAuthResponse()
            .status(AuthResponseStatus.NEW_USER)
            .descr("Авторизация пройдена, новый пользователь");
    private static final ConfirmAuthResponse SUCCESS = new ConfirmAuthResponse()
            .status(AuthResponseStatus.SUCCESS)
            .descr("Авторизация пройдена");
    private static final ConfirmAuthResponse WRONG_CREDS = new ConfirmAuthResponse()
            .status(AuthResponseStatus.WRONG_CODE)
            .descr("Введен неверный код");


    public void sendSms(String phone) {
        var seq = authSmsSender.generateAndSendAuthSequence();
        userService.updateLastSeq(phone, seq);
    }

    public ConfirmAuthResponse validateSeq(String phone, String seq) {
        var user = userService.findUser(phone);
        if (user == null) {
            throw new AppException("Неверные данные для авторизации", HttpStatus.UNAUTHORIZED);
        }
        if (user.getLastSentSeq().equals(seq)) {
            user = userService.updateToken(phone);
            AuthUtil.saveUserToContext(user);
            return user.isNewUser() ? SUCCESS_NEW : SUCCESS;
        }
        return WRONG_CREDS;
    }

    public UserPojo findAuthenticationInfo(String token) {
        return userService.findUserByToken(UUID.fromString(token));
    }

}

package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.LoginApi;
import org.example.blabla.model.ConfirmAuthRequest;
import org.example.blabla.model.DefaultResponse;
import org.example.blabla.model.DefaultResponseStatus;
import org.example.blabla.security.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AuthController implements LoginApi {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<DefaultResponse> confirmAuth(String phone, ConfirmAuthRequest confirmAuthRequest) {
        var response = new DefaultResponse();
        response = securityService.validateSeq(phone, confirmAuthRequest.getConfirmationSequence())
                ? response.status(DefaultResponseStatus.SUCCESS).descr("Авторизация пройдена")
                : response.status(DefaultResponseStatus.ERROR).descr("Введен неверный код");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DefaultResponse> sendLoginInfo(String phone) {
        securityService.sendSms(phone);
        return ResponseEntity.ok(new DefaultResponse().descr("Смс выслан").status(DefaultResponseStatus.SUCCESS));
    }
}

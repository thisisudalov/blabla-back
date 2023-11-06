package org.example.blabla.security;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.user.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthSmsSender authSmsSender;
    private final UserService userService;

    public void sendSms(String phone) {
        var seq = authSmsSender.generateAndSendAuthSequence();
        userService.updateLastSeq(phone, seq);
    }

    public boolean validateSeq(String phone, String seq) {
        return userService.getUser(phone).getLastSentSeq().equals(seq);
    }

    public boolean isUserAuthenticated(String token) {
        return userService.findUserByToken(UUID.fromString(token)) != null;
    }

}

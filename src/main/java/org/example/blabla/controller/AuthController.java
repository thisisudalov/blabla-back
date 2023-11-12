package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.LoginApi;
import org.example.blabla.model.*;
import org.example.blabla.security.SecurityService;
import org.example.blabla.util.AuthUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AuthController implements LoginApi {

    private final SecurityService securityService;

    @Override
    public ResponseEntity<DefaultResponse> sendLoginInfo(String phone) {
        securityService.sendSms(phone);
        return ResponseEntity.ok(new DefaultResponse().descr("Смс выслан").status(DefaultResponseStatus.SUCCESS));
    }

    @Override
    public ResponseEntity<ConfirmAuthResponse> confirmAuth(String phone, ConfirmAuthRequest confirmAuthRequest) {
        var response = securityService.validateSeq(phone, confirmAuthRequest.getConfirmationSequence());
        var headers = new HttpHeaders();
        headers.add("Authorization", AuthUtil.getUserFromContext().getToken().toString());
        return ResponseEntity.ok().headers(headers).body(response);
    }
}

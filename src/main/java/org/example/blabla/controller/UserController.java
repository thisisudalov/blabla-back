package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.UserApi;
import org.example.blabla.domain.user.UserService;
import org.example.blabla.model.UserRequest;
import org.example.blabla.model.UserResponse;
import org.example.blabla.security.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @Authenticated
    public ResponseEntity<UserResponse> updateUserInfo(String phone, UserRequest userRequest) {
        return ResponseEntity.ok(new UserResponse().username(userService.updateUser(phone, userRequest).getUserName()));
    }
}

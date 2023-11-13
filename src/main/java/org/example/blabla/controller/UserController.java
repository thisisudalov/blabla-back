package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.UserApi;
import org.example.blabla.domain.user.UserMapper;
import org.example.blabla.domain.user.UserService;
import org.example.blabla.model.*;
import org.example.blabla.security.Authenticated;
import org.example.blabla.util.AuthUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @Authenticated
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok(userMapper.map(userService.getUser(AuthUtil.getUserFromContext().getPhoneNumber())));
    }

    @Override
    @Authenticated
    public ResponseEntity<UserNotificationsDto> getUserNotificationsInfo() {
        return ResponseEntity.ok(userMapper.mapNotifications(userService.getUser(AuthUtil.getUserFromContext().getPhoneNumber())));
    }

    @Override
    @Authenticated
    public ResponseEntity<DefaultResponse> updatePhoneNumber() {
        return null;
    }

    @Override
    @Authenticated
    public ResponseEntity<UserDto> updateUserInfo(UserDto userRequest) {
        return ResponseEntity.ok(userMapper.map(userService.updateUser(AuthUtil.getUserFromContext().getPhoneNumber(), userRequest)));
    }

    @Override
    @Authenticated
    public ResponseEntity<UserNotificationsDto> updateUserNotificationsInfo(UserNotificationsDto userNotificationsDto) {
        return ResponseEntity.ok(userMapper.mapNotifications(userService.updateUser(AuthUtil.getUserFromContext().getPhoneNumber(), userNotificationsDto)));
    }

    @Override
    @Authenticated
    public ResponseEntity<DefaultResponse> userDeleteAvatar() {
        userService.deleteAvatar();
        return ResponseEntity.ok(new DefaultResponse().status(DefaultResponseStatus.SUCCESS));
    }

    @Override
    @Authenticated
    public ResponseEntity<Resource> userGetAvatar() {
        return ResponseEntity.ok(userService.getAvatar());
    }

    @Override
    @Authenticated
    public ResponseEntity<Resource> userSetAvatar(Resource body) {
        try {
            return ResponseEntity.ok(userService.setAvatar(body));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.UserApi;
import org.example.blabla.domain.avatar.UserAvatarService;
import org.example.blabla.domain.user.UserMapper;
import org.example.blabla.domain.user.UserService;
import org.example.blabla.model.*;
import org.example.blabla.security.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserAvatarService userAvatarService;
    private final UserMapper userMapper;

    @Override
    @Authenticated
    public ResponseEntity<UserDto> getUserInfo(String phone) {
        return ResponseEntity.ok(userMapper.map(userService.getUser(phone)));
    }

    @Override
    @Authenticated
    public ResponseEntity<UserNotificationsDto> getUserNotificationsInfo(String phone) {
        return ResponseEntity.ok(userMapper.mapNotifications(userService.getUser(phone)));
    }

    @Override
    @Authenticated
    public ResponseEntity<DefaultResponse> updatePhoneNumber(String phone) {
        return null;
    }

    @Override
    @Authenticated
    public ResponseEntity<UserDto> updateUserInfo(String phone, UserDto userRequest) {
        return ResponseEntity.ok(userMapper.map(userService.updateUser(phone, userRequest)));
    }

    @Override
    @Authenticated
    public ResponseEntity<UserNotificationsDto> updateUserNotificationsInfo(String phone, UserNotificationsDto userNotificationsDto) {
        return ResponseEntity.ok(userMapper.mapNotifications(userService.updateUser(phone, userNotificationsDto)));
    }

    @Override
    @Authenticated
    public ResponseEntity<UserAvatarDto> userDeleteAvatar(String phone) {
        return ResponseEntity.ok(userAvatarService.deleteAvatar());
    }

    @Override
    @Authenticated
    public ResponseEntity<UserAvatarDto> userGetAvatar(String phone) {
        return ResponseEntity.ok(userAvatarService.getAvatar());
    }

    @Override
    @Authenticated
    public ResponseEntity<UserAvatarDto> userSetAvatar(String phone, UserAvatarDto userAvatar) {
        return ResponseEntity.ok(userAvatarService.setAvatar(userAvatar));
    }

}

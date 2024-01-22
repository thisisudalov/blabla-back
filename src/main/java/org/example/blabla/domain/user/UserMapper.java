package org.example.blabla.domain.user;

import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.model.UserDto;
import org.example.blabla.model.UserNotificationsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPojo map(UserEntity source);

    @Mapping(target = "username", source = "userName")
    UserDto map(UserPojo source);

    @Mapping(target = "statusChanging", source = "notificationOnStatusChange")
    @Mapping(target = "newMembers", source = "notificationsOnNewMember")
    UserNotificationsDto mapNotifications(UserPojo source);

}

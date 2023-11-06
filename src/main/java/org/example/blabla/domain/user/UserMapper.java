package org.example.blabla.domain.user;

import org.example.blabla.domain.pojo.UserPojo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPojo map(UserEntity source);

}

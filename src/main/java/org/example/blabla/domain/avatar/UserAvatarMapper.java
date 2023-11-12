package org.example.blabla.domain.avatar;

import org.example.blabla.model.UserAvatarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAvatarMapper {

    UserAvatarDto map(UserAvatar source);

}

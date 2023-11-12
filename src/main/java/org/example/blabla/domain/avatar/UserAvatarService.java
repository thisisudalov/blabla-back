package org.example.blabla.domain.avatar;

import lombok.RequiredArgsConstructor;
import org.example.blabla.model.UserAvatarDto;
import org.example.blabla.util.AuthUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAvatarService {

    private final UserAvatarRepo avatarRepo;
    private final UserAvatarMapper mapper;

    public UserAvatarDto setAvatar(UserAvatarDto avatar) {
        var avatarEntity = avatarRepo.findByUserPhone(AuthUtil.getUserFromContext().getPhoneNumber());
        avatarEntity.setData(avatarEntity.getData());
        return mapper.map(avatarEntity);
    }

    public UserAvatarDto getAvatar() {
        return mapper.map(avatarRepo.findByUserPhone(AuthUtil.getUserFromContext().getPhoneNumber()));
    }

    public UserAvatarDto deleteAvatar() {
        return mapper.map(avatarRepo.deleteByUserPhone(AuthUtil.getUserFromContext().getPhoneNumber()));
    }

}

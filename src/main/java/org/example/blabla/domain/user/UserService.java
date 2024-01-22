package org.example.blabla.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.UserDto;
import org.example.blabla.model.UserNotificationsDto;
import org.example.blabla.util.AuthUtil;
import org.example.blabla.util.PhoneUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public void updateLastSeq(String userPhone, String seq) {
        findOptionalUser(userPhone).ifPresentOrElse(
                userEntity ->
                {
                    userEntity.setLastSentSeq(seq);
                    userRepo.save(userEntity);
                    },

                () ->
                {
                    createUser(userPhone, seq);
                    }
                );
    }

    public UserPojo updateToken(String phone) {
        var user = getUserEntity(phone);
        user.setToken(UUID.randomUUID());
        return userMapper.map(userRepo.save(user));
    }

    public UserPojo getUser() {
        return getUser(AuthUtil.getUserFromContext().getPhoneNumber());
    }

    public UserPojo getUser(String phone) {
        return userMapper.map(getUserEntity(phone));
    }

    public UserPojo findUser(String phone) {
        return userMapper.map(findUserEntity(phone));
    }

    public UserPojo findUserByToken(UUID token) {
        return userMapper.map(userRepo.findByToken(token));
    }

    public UserPojo createUser(String phone, String seq) {
        if (findUserEntity(phone) != null) {
            throw new AppException("Пользователь с номером " + phone + " уже существует");
        }
        return userMapper.map(createUserEntity(phone, seq));
    }

    public UserPojo updateUser(String phone, UserDto userRequest) {
        var updatedUser = getUserEntity(phone);
        updatedUser.setUserName(userRequest.getUsername());
        updatedUser.setNewUser(false);
        updatedUser.setAboutMe(userRequest.getAboutMe());
        updatedUser.setBirthday(userRequest.getBirthday());
        return userMapper.map(userRepo.save(updatedUser));
    }

    public UserPojo updateUser(String phone, UserNotificationsDto userRequest) {
        var updatedUser = getUserEntity(phone);
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setNotificationsOnNewMember(userRequest.getNewMembers());
        updatedUser.setNotificationsOnStatusChange(userRequest.getStatusChanging());
        return userMapper.map(userRepo.save(updatedUser));
    }

    public ByteArrayResource setAvatar(Resource body) throws IOException {
        var user = getUserEntity(AuthUtil.getUserFromContext().getPhoneNumber());
        user.getUserAvatar().setData(body.getContentAsByteArray());
        return new ByteArrayResource(userRepo.save(user).getUserAvatar().getData());
    }

    @Transactional
    public ByteArrayResource getAvatar() {
        return new ByteArrayResource(getUserEntity(AuthUtil.getUserFromContext().getPhoneNumber()).getUserAvatar().getData());
    }

    public void deleteAvatar() {
        var user = getUserEntity(AuthUtil.getUserFromContext().getPhoneNumber());
        user.getUserAvatar().setData(null);
        userRepo.save(user);
    }

    private UserEntity createUserEntity(String phone, String seq) {
        var newUser = new UserEntity();
        newUser.setPhoneNumber(PhoneUtil.cutPhoneNumber(phone));
        newUser.setLastSentSeq(seq);
        newUser.setNewUser(true);
        newUser.setUserAvatar(new UserAvatar());

        return userRepo.save(newUser);
    }

    public UserEntity getUserEntity(String phone) {
        return findOptionalUser(phone).orElseThrow(() -> new AppException("Не найден пользователь"));
    }

    public UserEntity getUserEntity() {
        return getUserEntity(AuthUtil.getUserFromContext().getPhoneNumber());
    }

    public UserEntity findUserEntity(String phone) {
        return findOptionalUser(phone).orElse(null);
    }

    public Optional<UserEntity> findOptionalUser(String phone) {
        return userRepo.findByPhoneNumber(PhoneUtil.cutPhoneNumber(phone));
    }

}

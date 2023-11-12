package org.example.blabla.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.UserRequest;
import org.example.blabla.util.PhoneUtil;
import org.springframework.stereotype.Service;

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

    public UserPojo updateUser(String phone, UserRequest userRequest) {
        var updatedUser = getUserEntity(phone);
        updatedUser.setUserName(userRequest.getUsername());
        return userMapper.map(userRepo.save(updatedUser));
    }

    private UserEntity createUserEntity(String phone, String seq) {
        var newUser = new UserEntity();
        newUser.setPhoneNumber(PhoneUtil.cutPhoneNumber(phone));
        newUser.setLastSentSeq(seq);
        newUser.setNewUser(true);

        return userRepo.save(newUser);
    }

    private UserEntity getUserEntity(String phone) {
        return findOptionalUser(phone).orElseThrow(() -> new AppException("Не найден пользователь"));
    }

    private UserEntity findUserEntity(String phone) {
        return findOptionalUser(phone).orElse(null);
    }

    private Optional<UserEntity> findOptionalUser(String phone) {
        return userRepo.findByPhoneNumber(PhoneUtil.cutPhoneNumber(phone));
    }

}

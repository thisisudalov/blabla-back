package org.example.blabla.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.exception.AppException;
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
        getUserEntity(userPhone).setLastSentSeq(seq);
    }

    public UserPojo getUser(String phone) {
        return userMapper.map(findUserEntity(phone));
    }

    public UserPojo findUser(String phone) {
        return userMapper.map(findUserEntity(phone));
    }

    public UserPojo findUserByToken(UUID token) {
        return userMapper.map(userRepo.findByToken(token));
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

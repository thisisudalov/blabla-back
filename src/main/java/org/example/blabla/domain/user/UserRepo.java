package org.example.blabla.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

interface UserRepo extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByPhoneNumber(String phone);

    UserEntity findByToken(UUID token);

}

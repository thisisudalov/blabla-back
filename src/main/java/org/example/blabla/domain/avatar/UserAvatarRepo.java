package org.example.blabla.domain.avatar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserAvatarRepo extends CrudRepository<UserAvatar, UUID> {

    @Query(value = "select * from user_avatar ua join user u on ua.id = u.avatar_id where u.phone = ?1",
            nativeQuery = true)
    UserAvatar findByUserPhone(String phone);

    @Query(value = "delete from user_avatar ua join user u on ua.id = u.avatar_id where u.phone = ?1",
            nativeQuery = true)
    UserAvatar deleteByUserPhone(String phone);
}

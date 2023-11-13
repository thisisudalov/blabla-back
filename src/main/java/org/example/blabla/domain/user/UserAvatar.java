package org.example.blabla.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.blabla.domain.user.UserEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "user_avatar")
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserAvatar {

    @UuidGenerator
    @Id
    private UUID id;

    @Lob
    @Column(name = "data", columnDefinition="BLOB")
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "avatar_id")
    private UserEntity user;

}

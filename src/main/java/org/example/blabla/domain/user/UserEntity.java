package org.example.blabla.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.blabla.domain.avatar.UserAvatar;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Table(name = "user")
public class UserEntity {

    @UuidGenerator
    @Id
    private UUID id;

    private String phoneNumber;

    private String userName;

    private String lastSentSeq;

    private UUID token;

    private boolean newUser;

    private String username;

    private String aboutMe;

    private String email;

    private LocalDate birthday;

    private Boolean notificationOnStatusChange;

    private Boolean notificationsOnNewMember;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "avatar_id")
    private UserAvatar userAvatar;

}

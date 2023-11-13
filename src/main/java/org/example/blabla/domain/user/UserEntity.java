package org.example.blabla.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Table(name = "app_user")
public class UserEntity {

    @UuidGenerator
    @Id
    private UUID id;

    private String phoneNumber;

    private String userName;

    private String lastSentSeq;

    private UUID token;

    private boolean newUser;

    private String aboutMe;

    private String email;

    private LocalDate birthday;

    private Boolean notificationsOnStatusChange;

    private Boolean notificationsOnNewMember;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private UserAvatar userAvatar;

}

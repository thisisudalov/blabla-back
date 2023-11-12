package org.example.blabla.domain.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
public class UserPojo {

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

}

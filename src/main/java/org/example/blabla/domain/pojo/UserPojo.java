package org.example.blabla.domain.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

}

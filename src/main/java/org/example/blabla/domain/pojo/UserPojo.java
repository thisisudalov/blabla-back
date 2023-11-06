package org.example.blabla.domain.pojo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPojo {

    private String phoneNumber;

    private String userName;

    private String lastSentSeq;

}

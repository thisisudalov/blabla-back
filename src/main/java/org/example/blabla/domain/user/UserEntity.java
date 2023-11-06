package org.example.blabla.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserEntity {

    @UuidGenerator
    @Id
    private UUID id;

    private String phoneNumber;

    private String userName;

    private String lastSentSeq;

    private UUID token;

}

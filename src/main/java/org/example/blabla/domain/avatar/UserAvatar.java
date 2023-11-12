package org.example.blabla.domain.avatar;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "avatar")
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserAvatar {

    @UuidGenerator
    @Id
    private UUID id;

    @Lob
    @Column(name = "data", columnDefinition="BLOB")
    private byte[] data;

}

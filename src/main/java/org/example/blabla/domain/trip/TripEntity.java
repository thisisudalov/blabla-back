package org.example.blabla.domain.trip;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.blabla.domain.user.UserEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trip")
public class TripEntity {

    @UuidGenerator
    @Id
    private UUID id;

    private String comment;

    @Column(name = "seats")
    private Integer seatsCount;

    @OneToOne
    private UserEntity createdBy;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<RouteEntity> routes;

}

package org.example.blabla.domain.trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.blabla.domain.city.LocationEntity;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "route")
public class RouteEntity {

    @UuidGenerator
    @Id
    private UUID id;

    private String comment;

    @Column(name = "date_time")
    private OffsetDateTime datetime;

    private Integer cost;

    @OneToOne
    private LocationEntity location;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TripEntity trip;

}

package org.example.blabla.domain.city;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "location")
@Getter
@Setter(AccessLevel.PACKAGE)
public class LocationEntity {

    @Id
    @UuidGenerator
    private UUID id;

    private String address;

    private String postalCode;

    private String country;

    private String federalDistrict;

    private String regionType;

    private String region;

    private String areaType;

    private String area;

    private String cityType;

    private String city;

}

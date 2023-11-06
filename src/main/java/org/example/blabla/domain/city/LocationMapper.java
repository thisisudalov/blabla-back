package org.example.blabla.domain.city;

import org.example.blabla.model.LocationItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    List<LocationItem> map(List<LocationEntity> source);

    LocationItem map(LocationEntity source);

}

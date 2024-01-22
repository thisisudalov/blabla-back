package org.example.blabla.domain.trip;

import org.example.blabla.domain.city.LocationService;
import org.example.blabla.domain.user.UserEntity;
import org.example.blabla.model.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TripMapper {

    @Autowired
    protected LocationService locationService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "routes", ignore = true)
    @Mapping(target = "createdBy", source = "user")
    abstract TripEntity mapCreation(CreateTripRequestDto source, UserEntity user);

    abstract TripResponseDto map(TripEntity source);

    @Mapping(target = "city", expression = "java(source.getLocation().getId())")
    abstract TripRouteDto map(RouteEntity source);

    public abstract List<RouteEntity> map(List<CreateTripRouteRequestDto> source, @Context TripEntity trip);

    @Mapping(target = "routes", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract TripEntity mapUpdate(@MappingTarget TripEntity target, UpdateTripRequestDto source);

    public abstract List<RouteEntity> mapRoutes(List<TripRouteDto> source);

    @Mapping(target = "trip", ignore = true)
    @Mapping(target = "location", expression = "java(locationService.getById(source.getCity()))")
    abstract RouteEntity map(TripRouteDto source);

    RouteEntity map(CreateTripRouteRequestDto source, @Context TripEntity trip) {
        if (source == null) {
            return null;
        }

        var target = new RouteEntity();
        target.setTrip(trip);
        target.setId(null);
        target.setDatetime(source.getDatetime());
        target.setComment(source.getComment());
        target.setCost(source.getCost());
        target.setLocation(locationService.getById(source.getCity()));

        return target;
    }

}

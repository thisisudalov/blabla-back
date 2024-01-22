package org.example.blabla.domain.trip;

import lombok.RequiredArgsConstructor;
import org.example.blabla.model.TripRouteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository repository;
    private final TripMapper tripMapper;

    // todo доделать поиск и обновление по id + что там со временем
    public List<RouteEntity> updateRoutes(TripEntity trip, List<TripRouteDto> source) {
        var newRoutes = tripMapper.mapRoutes(source);
        newRoutes.forEach(routeEntity -> routeEntity.setTrip(trip));
        var newRouteIds = newRoutes
                .stream()
                .map(RouteEntity::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        trip.getRoutes().forEach(routeEntity -> {
            if (!newRouteIds.contains(routeEntity.getId())) {
                repository.delete(routeEntity);
            }
        });
        repository.saveAll(newRoutes);
        return newRoutes;
    }

}

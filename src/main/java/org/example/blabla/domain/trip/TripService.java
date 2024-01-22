package org.example.blabla.domain.trip;

import lombok.RequiredArgsConstructor;
import org.example.blabla.domain.user.UserService;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TripService {

    private final TripMapper tripMapper;
    private final UserService userService;
    private final RouteService routeService;
    private final TripRepository repository;

    public TripResponseDto createTrip(CreateTripRequestDto request) {
        var user = userService.getUserEntity();
        var newEntity = tripMapper.mapCreation(request, user);
        var routes = tripMapper.map(request.getRoutes(), newEntity);
        newEntity.setRoutes(routes);

        return tripMapper.map(repository.save(newEntity));
    }

    public TripResponseDto getTrip(UUID tripId) {
        return tripMapper.map(getTripById(tripId));
    }

    public InfoResponse deleteTrip(UUID tripId) {
        repository.deleteById(tripId);
        return new InfoResponse().time(LocalDateTime.now().toString()).message("Объект удален");
    }

    public TripResponseDto updateTrip(UUID tripId, UpdateTripRequestDto request) {
        var entity = getTripById(tripId);
        var routes = routeService.updateRoutes(entity, request.getRoutes());
        var newEntityState = tripMapper.mapUpdate(entity, request);
        newEntityState.setRoutes(routes);
        repository.save(newEntityState);

        return tripMapper.map(newEntityState);
    }


    private TripEntity getTripById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new AppException("Не найдена данная поездка"));
    }

}

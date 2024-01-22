package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.TripApi;
import org.example.blabla.domain.trip.TripService;
import org.example.blabla.model.CreateTripRequestDto;
import org.example.blabla.model.InfoResponse;
import org.example.blabla.model.TripResponseDto;
import org.example.blabla.model.UpdateTripRequestDto;
import org.example.blabla.security.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class TripController implements TripApi {

    private final TripService tripService;

    @Override
    @Authenticated
    public ResponseEntity<TripResponseDto> createTrip(CreateTripRequestDto createTripRequestDto) {
        return ResponseEntity.ok(tripService.createTrip(createTripRequestDto));
    }

    @Override
    @Authenticated
    public ResponseEntity<InfoResponse> deleteTrip(UUID tripId) {
        return ResponseEntity.ok(tripService.deleteTrip(tripId));
    }

    @Override
    @Authenticated
    public ResponseEntity<TripResponseDto> getTripInfo(UUID tripId) {
        return ResponseEntity.ok(tripService.getTrip(tripId));
    }

    @Override
    public ResponseEntity<TripResponseDto> updateTrip(UUID tripId, UpdateTripRequestDto updateTripRequestDto) {
        return ResponseEntity.ok(tripService.updateTrip(tripId, updateTripRequestDto));
    }

}

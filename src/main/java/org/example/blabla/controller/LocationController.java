package org.example.blabla.controller;

import lombok.RequiredArgsConstructor;
import org.example.blabla.api.LocationApi;
import org.example.blabla.domain.city.LocationService;
import org.example.blabla.model.LocationResponse;
import org.example.blabla.security.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LocationController implements LocationApi {

    private final LocationService locationService;

    @Override
    @Authenticated
    public ResponseEntity<LocationResponse> findLocationLike(String query) {
        return ResponseEntity.ok(new LocationResponse().items(locationService.findLocationLikeAddress(query)));
    }
}

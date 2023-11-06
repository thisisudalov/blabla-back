package org.example.blabla.domain.city;

import lombok.RequiredArgsConstructor;
import org.example.blabla.model.LocationItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepo locationRepo;
    private final LocationMapper locationMapper;

    public List<LocationItem> findLocationLikeAddress(String address) {
        return locationMapper.map(locationRepo.findByAddressLike(address));
    }

}

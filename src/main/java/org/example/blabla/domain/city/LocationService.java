package org.example.blabla.domain.city;

import lombok.RequiredArgsConstructor;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.LocationItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepo locationRepo;
    private final LocationMapper locationMapper;

    public LocationEntity getById(UUID id) {
        return locationRepo.findById(id).orElseThrow(() -> new AppException("Не найдено точки с id " + id));
    }

    public List<LocationItem> findLocationLikeAddress(String address) {
        return locationMapper.map(locationRepo.findByAddressLike(address));
    }

}

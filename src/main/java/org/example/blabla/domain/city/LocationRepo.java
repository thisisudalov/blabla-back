package org.example.blabla.domain.city;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

interface LocationRepo extends JpaRepository<LocationEntity, UUID> {

    default List<LocationEntity> findByAddressLike(String address) {
        return findByAddressLikeIgnoreCase("%".concat(address).concat("%"), PageRequest.of(0, 5));
    }

    List<LocationEntity> findByAddressLikeIgnoreCase(String address, Pageable pageable);

}

package org.example.blabla.domain.trip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<RouteEntity, UUID> {
}

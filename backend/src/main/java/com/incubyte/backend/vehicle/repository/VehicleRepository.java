package com.incubyte.backend.vehicle.repository;

import com.incubyte.backend.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
package com.incubyte.backend.vehicle.repository;

import com.incubyte.backend.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository responsible for Vehicle persistence.
 */
public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    /**
     * Search vehicles by make.
     */
    List<Vehicle> findByMakeContainingIgnoreCase(
            String make
    );

    /**
     * Search vehicles by model.
     */
    List<Vehicle> findByModelContainingIgnoreCase(
            String model
    );

    /**
     * Search vehicles by category.
     */
    List<Vehicle> findByCategoryContainingIgnoreCase(
            String category
    );

}
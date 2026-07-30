package com.incubyte.backend.vehicle.repository;

import com.incubyte.backend.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository responsible for Vehicle persistence.
 */
public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    /**
     * Unified search using optional filters.
     */
    @Query("""

            SELECT v

            FROM Vehicle v

            WHERE

            (:make IS NULL
                OR LOWER(v.make)
                LIKE LOWER(CONCAT('%', :make, '%')))

            AND

            (:model IS NULL
                OR LOWER(v.model)
                LIKE LOWER(CONCAT('%', :model, '%')))

            AND

            (:category IS NULL
                OR LOWER(v.category)
                LIKE LOWER(CONCAT('%', :category, '%')))

            AND

            (:minPrice IS NULL
                OR v.price >= :minPrice)

            AND

            (:maxPrice IS NULL
                OR v.price <= :maxPrice)

            """)
    List<Vehicle> searchVehicles(

            @Param("make")
            String make,

            @Param("model")
            String model,

            @Param("category")
            String category,

            @Param("minPrice")
            Double minPrice,

            @Param("maxPrice")
            Double maxPrice

    );

}
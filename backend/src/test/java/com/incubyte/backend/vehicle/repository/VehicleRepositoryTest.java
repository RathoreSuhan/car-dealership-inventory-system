package com.incubyte.backend.vehicle.repository;

import com.incubyte.backend.vehicle.entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository repository;

    @Test
    void shouldSearchVehicleByMake() {

        repository.save(
                Vehicle.builder()
                        .make("Toyota")
                        .model("Fortuner")
                        .category("SUV")
                        .price(4200000.0)
                        .quantity(5)
                        .build()
        );

        repository.save(
                Vehicle.builder()
                        .make("Honda")
                        .model("City")
                        .category("Sedan")
                        .price(1500000.0)
                        .quantity(8)
                        .build()
        );

        List<Vehicle> vehicles = repository.searchVehicles(
                "Toyota",
                null,
                null,
                null,
                null
        );

        assertEquals(1, vehicles.size());
    }
}
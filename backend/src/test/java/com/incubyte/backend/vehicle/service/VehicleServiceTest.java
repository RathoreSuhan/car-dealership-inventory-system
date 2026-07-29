package com.incubyte.backend.vehicle.service;

import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.entity.Vehicle;
import com.incubyte.backend.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Test
    void shouldAddVehicleSuccessfully() {

        VehicleRepository repository =
                mock(VehicleRepository.class);

        VehicleService service =
                new VehicleService(repository);

        CreateVehicleRequest request =
                new CreateVehicleRequest(

                        "Toyota",
                        "Fortuner",
                        "SUV",
                        4200000.0,
                        10

                );

        when(repository.save(any(Vehicle.class)))
                .thenAnswer(invocation -> {

                    Vehicle vehicle =
                            invocation.getArgument(0);

                    vehicle.setId(1L);

                    return vehicle;

                });

        assertNotNull(

                service.addVehicle(request)

        );

    }

}
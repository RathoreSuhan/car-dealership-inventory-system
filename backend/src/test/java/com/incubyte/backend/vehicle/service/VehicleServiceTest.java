package com.incubyte.backend.vehicle.service;

import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.dto.VehicleResponse;
import com.incubyte.backend.vehicle.entity.Vehicle;
import com.incubyte.backend.vehicle.service.VehicleService;
import com.incubyte.backend.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


    @Test
    void shouldUpdateVehicleSuccessfully() {

        // Arrange
        VehicleRepository repository = mock(VehicleRepository.class);

        VehicleService service = new VehicleService(repository);

        Vehicle existingVehicle = Vehicle.builder()
                .id(1L)
                .make("Toyota")
                .model("Fortuner")
                .category("SUV")
                .price(4200000.0)
                .quantity(5)
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingVehicle));

        when(repository.save(any(Vehicle.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateVehicleRequest request =
                new CreateVehicleRequest(
                        "Toyota",
                        "Legender",
                        "SUV",
                        4500000.0,
                        8
                );

        // Act
        VehicleResponse response =
                service.updateVehicle(1L, request);

        // Assert
        assertEquals("Legender", response.getModel());
        assertEquals(8, response.getQuantity());

        verify(repository).save(existingVehicle);
    }

    @Test
    void shouldPurchaseVehicleSuccessfully() {

        // Arrange

        VehicleRepository repository =
                mock(VehicleRepository.class);

        VehicleService service =
                new VehicleService(repository);

        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .make("Toyota")
                .model("Fortuner")
                .category("SUV")
                .price(4200000.0)
                .quantity(5)
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(vehicle));

        when(repository.save(any(Vehicle.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act

        VehicleResponse response =
                service.purchaseVehicle(1L);

        // Assert

        assertEquals(
                4,
                response.getQuantity()
        );

        verify(repository)
                .save(vehicle);

    }

    @Test
    void shouldRestockVehicleSuccessfully() {

        // Arrange

        VehicleRepository repository =
                mock(VehicleRepository.class);

        VehicleService service =
                new VehicleService(repository);

        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .make("Toyota")
                .model("Legender")
                .category("SUV")
                .price(4500000.0)
                .quantity(5)
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(vehicle));

        when(repository.save(any(Vehicle.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act

        VehicleResponse response =
                service.restockVehicle(
                        1L,
                        10
                );

        // Assert

        assertEquals(
                15,
                response.getQuantity()
        );

        verify(repository)
                .save(vehicle);

    }


    @Test
    void shouldDeleteVehicleSuccessfully() {

        // Arrange

        VehicleRepository repository =
                mock(VehicleRepository.class);

        VehicleService service =
                new VehicleService(repository);

        Vehicle vehicle = Vehicle.builder()

                .id(1L)
                .make("Toyota")
                .model("Legender")
                .category("SUV")
                .price(4500000.0)
                .quantity(5)

                .build();

        when(repository.findById(1L))

                .thenReturn(Optional.of(vehicle));

        // Act

        service.deleteVehicle(1L);

        // Assert

        verify(repository)

                .delete(vehicle);

    }
}
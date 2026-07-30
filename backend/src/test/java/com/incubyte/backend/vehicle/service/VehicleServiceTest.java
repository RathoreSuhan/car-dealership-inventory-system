package com.incubyte.backend.vehicle.service;

import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.dto.VehicleResponse;
import com.incubyte.backend.vehicle.entity.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.incubyte.backend.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class VehicleServiceTest {

    @Mock
    private VehicleRepository repository;

    @InjectMocks
    private VehicleService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddVehicleSuccessfully() {

        CreateVehicleRequest request = createRequest();

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

        Vehicle existingVehicle = createVehicle();

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingVehicle));

        when(repository.save(any(Vehicle.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateVehicleRequest request = createRequest();

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

        Vehicle vehicle = createVehicle();

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

        Vehicle vehicle = createVehicle();

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

        Vehicle vehicle = createVehicle();

        when(repository.findById(1L))

                .thenReturn(Optional.of(vehicle));

        // Act

        service.deleteVehicle(1L);

        // Assert

        verify(repository)

                .delete(vehicle);

    }


    private Vehicle createVehicle() {

        return Vehicle.builder()

                .id(1L)

                .make("Toyota")

                .model("Legender")

                .category("SUV")

                .price(4500000.0)

                .quantity(5)

                .build();

    }


    private CreateVehicleRequest createRequest() {

        return new CreateVehicleRequest(

                "Toyota",

                "Legender",

                "SUV",

                4500000.0,

                8

        );

    }
}
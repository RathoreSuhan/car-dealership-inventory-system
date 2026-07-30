package com.incubyte.backend.vehicle.controller;

import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.dto.VehicleResponse;
import com.incubyte.backend.vehicle.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * Add new vehicle.
     */
    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(

            @Valid
            @RequestBody
            CreateVehicleRequest request

    ) {

        return ResponseEntity

                .status(HttpStatus.CREATED)

                .body(

                        vehicleService.addVehicle(request)

                );

    }

    /**
     * Updates a vehicle.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(

            @PathVariable Long id,

            @Valid
            @RequestBody
            CreateVehicleRequest request

    ) {

        return ResponseEntity.ok(

                vehicleService.updateVehicle(
                        id,
                        request
                )

        );

    }

    /**
     * Purchases one vehicle.
     */
    @PostMapping("/{id}/purchase")
    public ResponseEntity<VehicleResponse> purchaseVehicle(

            @PathVariable Long id

    ) {

        return ResponseEntity.ok(

                vehicleService.purchaseVehicle(id)

        );

    }

    /**
     * Restocks an existing vehicle.
     */
    @PostMapping("/{id}/restock")
    public ResponseEntity<VehicleResponse> restockVehicle(

            @PathVariable Long id,

            @RequestParam Integer quantity

    ) {

        return ResponseEntity.ok(

                vehicleService.restockVehicle(
                        id,
                        quantity
                )

        );

    }

    /**
     * Get all vehicles.
     */
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {

        return ResponseEntity.ok(

                vehicleService.getAllVehicles()

        );

    }

    @GetMapping("/search/make")
    public ResponseEntity<List<VehicleResponse>> searchByMake(

            @RequestParam String make

    ) {

        return ResponseEntity.ok(

                vehicleService.searchByMake(make)

        );

    }

    @GetMapping("/search/model")
    public ResponseEntity<List<VehicleResponse>> searchByModel(
            @RequestParam String model
    ) {

        return ResponseEntity.ok(
                vehicleService.searchByModel(model)
        );

    }

    @GetMapping("/search/category")
    public ResponseEntity<List<VehicleResponse>> searchByCategory(

            @RequestParam String category

    ) {

        return ResponseEntity.ok(

                vehicleService.searchByCategory(category)

        );

    }

}
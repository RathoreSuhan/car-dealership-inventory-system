package com.incubyte.backend.vehicle.service;

import com.incubyte.backend.exception.InvalidRestockQuantityException;
import com.incubyte.backend.exception.OutOfStockException;
import com.incubyte.backend.exception.VehicleNotFoundException;
import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.dto.VehicleResponse;
import com.incubyte.backend.vehicle.entity.Vehicle;
import com.incubyte.backend.vehicle.mapper.VehicleMapper;
import com.incubyte.backend.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    // Repository used for database operations
    private final VehicleRepository vehicleRepository;

    /**
     * Adds a new vehicle.
     */
    public VehicleResponse addVehicle(CreateVehicleRequest request) {

        Vehicle vehicle = VehicleMapper.toEntity(request);

        vehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toResponse(vehicle);

    }

    /**
     * Updates an existing vehicle.
     */
    public VehicleResponse updateVehicle(
            Long id,
            CreateVehicleRequest request
    ) {

        // Fetch vehicle from database.
        Vehicle vehicle = getVehicleById(id);

        // Update all editable fields.
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setCategory(request.getCategory());
        vehicle.setPrice(request.getPrice());
        vehicle.setQuantity(request.getQuantity());

        // Persist updated entity.
        vehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toResponse(vehicle);
    }

    /**
     * Purchases one vehicle by reducing
     * available quantity.
     */
    public VehicleResponse purchaseVehicle(Long id) {

        Vehicle vehicle = getVehicleById(id);

        if (vehicle.getQuantity() <= 0) {

            throw new OutOfStockException(

                    "Vehicle is out of stock"

            );

        }

        vehicle.setQuantity(

                vehicle.getQuantity() - 1

        );

        vehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toResponse(vehicle);

    }

    /**
     * Increases available stock.
     */
    public VehicleResponse restockVehicle(

            Long id,
            Integer quantity

    ) {

        // Fetch vehicle.
        Vehicle vehicle = getVehicleById(id);

        // Quantity must always be positive.
        if (quantity <= 0) {

            throw new InvalidRestockQuantityException(

                    "Restock quantity must be greater than zero"

            );

        }

        // Increase stock.
        vehicle.setQuantity(

                vehicle.getQuantity() + quantity

        );

        // Persist changes.
        vehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toResponse(vehicle);

    }


    /**
     * Deletes a vehicle from inventory.
     */
    public void deleteVehicle(Long id) {

        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);

    }

    /**
     * Search vehicles using optional filters.
     */
    public List<VehicleResponse> searchVehicles(

            String make,

            String model,

            String category,

            Double minPrice,

            Double maxPrice

    ) {

        return vehicleRepository

                .searchVehicles(

                        make,

                        model,

                        category,

                        minPrice,

                        maxPrice

                )

                .stream()

                .map(VehicleMapper::toResponse)

                .toList();

    }

    /**
     * Returns all vehicles.
     */
    public List<VehicleResponse> getAllVehicles() {

        return vehicleRepository.findAll()

                .stream()

                .map(VehicleMapper::toResponse)

                .toList();

    }


    /**
     * Returns a vehicle if present,
     * otherwise throws VehicleNotFoundException.
     */
    private Vehicle getVehicleById(Long id) {

        return vehicleRepository

                .findById(id)

                .orElseThrow(

                        () -> new VehicleNotFoundException(

                                "Vehicle not found"

                        )

                );

    }

}
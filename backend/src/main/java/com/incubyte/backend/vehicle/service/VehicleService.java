package com.incubyte.backend.vehicle.service;

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

        Vehicle vehicle =
                VehicleMapper.toEntity(request);

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
        Vehicle vehicle = vehicleRepository

                .findById(id)

                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

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
     * Search by make.
     */
    public List<VehicleResponse> searchByMake(String make) {

        return vehicleRepository

                .findByMakeContainingIgnoreCase(make)

                .stream()

                .map(VehicleMapper::toResponse)

                .toList();

    }

    /**
     * Search by model.
     */
    public List<VehicleResponse> searchByModel(String model) {

        return vehicleRepository

                .findByModelContainingIgnoreCase(model)

                .stream()

                .map(VehicleMapper::toResponse)

                .toList();

    }

    /**
     * Search by category.
     */
    public List<VehicleResponse> searchByCategory(String category){

        return vehicleRepository

                .findByCategoryContainingIgnoreCase(category)

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

}
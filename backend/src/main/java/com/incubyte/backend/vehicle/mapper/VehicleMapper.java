package com.incubyte.backend.vehicle.mapper;

import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import com.incubyte.backend.vehicle.dto.VehicleResponse;
import com.incubyte.backend.vehicle.entity.Vehicle;

public final class VehicleMapper {

    private VehicleMapper() {}

    public static Vehicle toEntity(
            CreateVehicleRequest request
    ) {

        return Vehicle.builder()

                .make(request.getMake())
                .model(request.getModel())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())

                .build();

    }

    public static VehicleResponse toResponse(
            Vehicle vehicle
    ) {

        return VehicleResponse.builder()

                .id(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .category(vehicle.getCategory())
                .price(vehicle.getPrice())
                .quantity(vehicle.getQuantity())

                .build();

    }

}
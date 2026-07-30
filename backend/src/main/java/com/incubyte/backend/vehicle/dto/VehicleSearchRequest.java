package com.incubyte.backend.vehicle.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Request object containing optional
 * vehicle search filters.
 */
@Getter
@Setter
public class VehicleSearchRequest {

    private String make;

    private String model;

    private String category;

    private Double minPrice;

    private Double maxPrice;

}
package com.incubyte.backend.vehicle.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class VehicleResponse {

    private Long id;

    private String make;

    private String model;

    private String category;

    private Double price;

    private Integer quantity;

}
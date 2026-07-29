package com.incubyte.backend.vehicle.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequest {

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotBlank
    private String category;

    @Positive
    private Double price;

    @PositiveOrZero
    private Integer quantity;

}
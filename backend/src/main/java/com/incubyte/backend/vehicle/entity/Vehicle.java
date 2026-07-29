package com.incubyte.backend.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a vehicle available in the dealership.
 */
@Entity
@Table(name = "vehicles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vehicle manufacturer
    @Column(nullable = false)
    private String make;

    // Vehicle model
    @Column(nullable = false)
    private String model;

    // SUV, Sedan, Hatchback etc.
    @Column(nullable = false)
    private String category;

    // Selling price
    @Column(nullable = false)
    private Double price;

    // Available stock
    @Column(nullable = false)
    private Integer quantity;

}
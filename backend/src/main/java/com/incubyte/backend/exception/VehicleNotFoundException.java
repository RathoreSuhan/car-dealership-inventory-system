package com.incubyte.backend.exception;

/**
 * Thrown when a vehicle cannot be found.
 */
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
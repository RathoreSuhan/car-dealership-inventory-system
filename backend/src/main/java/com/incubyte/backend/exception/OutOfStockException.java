package com.incubyte.backend.exception;

/**
 * Thrown when a purchase is attempted
 * on an out-of-stock vehicle.
 */
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }

}
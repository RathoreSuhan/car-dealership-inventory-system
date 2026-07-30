package com.incubyte.backend.exception;

/**
 * Thrown when an invalid restock
 * quantity is provided.
 */
public class InvalidRestockQuantityException extends RuntimeException {

    public InvalidRestockQuantityException(String message){
        super(message);
    }

}
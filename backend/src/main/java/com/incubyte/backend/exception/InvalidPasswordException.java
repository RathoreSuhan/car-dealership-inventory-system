package com.incubyte.backend.exception;

/**
 * Thrown when password does not match.
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }

}
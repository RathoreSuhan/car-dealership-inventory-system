package com.incubyte.backend.exception;

/**
 * Thrown when no user exists with the given email.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
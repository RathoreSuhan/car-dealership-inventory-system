package com.incubyte.backend.exception;

/**
 * Thrown when a user tries to register
 * using an email address that already exists.
 */
public class EmailAlreadyExistsException
        extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;

/**
 * Handles authentication-related business operations.
 *
 * NOTE:
 * This is the first GREEN implementation.
 * We intentionally keep it tiny.
 */
public class AuthService {

    /**
     * Registers a new user.
     *
     * For the first GREEN step we are NOT interacting with the database.
     */
    public RegisterResponse register(RegisterRequest request) {

        return RegisterResponse.builder()
                .id(1L)
                .name(request.getName())
                .email(request.getEmail())
                .message("Registration successful")
                .build();

    }

}
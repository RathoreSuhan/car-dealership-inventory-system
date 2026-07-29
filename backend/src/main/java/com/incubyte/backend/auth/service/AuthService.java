package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.repository.UserRepository;

/**
 * Handles authentication use cases.
 */
public class AuthService {

    /**
     * Repository dependency.
     */
    private final UserRepository userRepository;

    /**
     * Constructor Injection.
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user.
     */
    public RegisterResponse register(RegisterRequest request) {

        // Business rule:
        // A user cannot register twice with the same email.
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(1L)
                .name(user.getName())
                .email(user.getEmail())
                .message("Registration successful")
                .build();

    }



}
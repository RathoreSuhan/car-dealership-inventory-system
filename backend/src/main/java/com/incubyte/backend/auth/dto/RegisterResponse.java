package com.incubyte.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Response returned after a successful registration.
 *
 * We never expose our Entity directly.
 * Instead, we expose a DTO tailored for the API response.
 */
@Getter
@Builder
@AllArgsConstructor
public class RegisterResponse {

    /**
     * Unique database identifier.
     */
    private Long id;

    /**
     * User's full name.
     */
    private String name;

    /**
     * User email.
     */
    private String email;

    /**
     * Success message.
     */
    private String message;

}
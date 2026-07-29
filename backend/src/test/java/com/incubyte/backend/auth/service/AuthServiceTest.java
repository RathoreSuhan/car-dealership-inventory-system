package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for authentication service.
 *
 * Following strict TDD:
 *
 * RED
 * GREEN
 * REFACTOR
 */
class AuthServiceTest {

    @Test
    void shouldRegisterNewUserSuccessfully() {

        // Arrange

        RegisterRequest request =
                new RegisterRequest(
                        "Suhan Kumar Singh",
                        "suhan@gmail.com",
                        "Password@123"
                );

        AuthService authService = new AuthService();

        // Act

        RegisterResponse response =
                authService.register(request);

        // Assert

        assertNotNull(response);

    }

}
package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthService.
 *
 * Pure unit tests:
 * No Spring Context.
 * No PostgreSQL.
 */
class AuthServiceTest {

    @Test
    void shouldRegisterNewUserSuccessfully() {

        // Arrange

        UserRepository repository = mock(UserRepository.class);

        AuthService authService =
                new AuthService(repository);

        RegisterRequest request =
                new RegisterRequest(
                        "Suhan Kumar Singh",
                        "suhan@gmail.com",
                        "Password@123"
                );

        // Act

        RegisterResponse response =
                authService.register(request);

        // Assert

        assertNotNull(response);

        verify(repository, times(1))
                .save(any());

    }

}
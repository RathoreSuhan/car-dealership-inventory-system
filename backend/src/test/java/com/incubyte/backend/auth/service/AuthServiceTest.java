package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.repository.UserRepository;
import com.incubyte.backend.exception.EmailAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
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

        UserRepository repository =
                mock(UserRepository.class);

        PasswordEncoder passwordEncoder =
                mock(PasswordEncoder.class);

        when(passwordEncoder.encode("Password@123"))
                .thenReturn("encoded-password");

        AuthService authService =
                new AuthService(
                        repository,
                        passwordEncoder
                );

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

        verify(repository)
                .save(any(User.class));

        verify(passwordEncoder)
                .encode("Password@123");

    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        // Arrange

        UserRepository repository =
                mock(UserRepository.class);

        PasswordEncoder passwordEncoder =
                mock(PasswordEncoder.class);

        when(repository.existsByEmail("suhan@gmail.com"))
                .thenReturn(true);

        AuthService authService =
                new AuthService(
                        repository,
                        passwordEncoder
                );

        RegisterRequest request =
                new RegisterRequest(
                        "Suhan Kumar Singh",
                        "suhan@gmail.com",
                        "Password@123"
                );

        // Act + Assert

        EmailAlreadyExistsException exception =
                assertThrows(
                        EmailAlreadyExistsException.class,
                        () -> authService.register(request)
                );

        assertEquals(
                "Email already registered",
                exception.getMessage()
        );

    }

}
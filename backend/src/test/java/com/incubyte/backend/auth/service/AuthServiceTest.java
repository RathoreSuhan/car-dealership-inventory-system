package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.LoginRequest;
import com.incubyte.backend.auth.dto.LoginResponse;
import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.repository.UserRepository;
import com.incubyte.backend.exception.EmailAlreadyExistsException;
import com.incubyte.backend.exception.UserNotFoundException;
import com.incubyte.backend.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

        JwtService jwtService =
                mock(JwtService.class);

        when(passwordEncoder.encode("Password@123"))
                .thenReturn("encoded-password");

        AuthService authService =
                new AuthService(
                        repository,
                        passwordEncoder,
                        jwtService
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

        assertAll(

                () -> assertEquals(
                        "Suhan Kumar Singh",
                        response.getName()),

                () -> assertEquals(
                        "suhan@gmail.com",
                        response.getEmail()),

                () -> assertEquals(
                        "Registration successful",
                        response.getMessage())

        );

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

        JwtService jwtService =
                mock(JwtService.class);

        when(repository.existsByEmail("suhan@gmail.com"))
                .thenReturn(true);

        AuthService authService =
                new AuthService(
                        repository,
                        passwordEncoder,
                        jwtService
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

    @Test
    void shouldLoginSuccessfully() {

        UserRepository repository = mock(UserRepository.class);

        PasswordEncoder encoder = mock(PasswordEncoder.class);

        JwtService jwtService = mock(JwtService.class);

        AuthService service = new AuthService(repository, encoder, jwtService);

        User user = User.builder()
                .id(1L)
                .name("Suhan")
                .email("suhan@gmail.com")
                .password("encoded-password")
                .build();

        when(repository.findByEmail("suhan@gmail.com"))
                .thenReturn(Optional.of(user));

        when(encoder.matches(
                "Password@123",
                "encoded-password"))
                .thenReturn(true);

        // JWT service behavior must be defined BEFORE login()
        when(jwtService.generateToken(user))
                .thenReturn("jwt-token");

        // Act
        LoginResponse response =
                service.login(
                        new LoginRequest(
                                "suhan@gmail.com",
                                "Password@123"
                        )
                );

        // Assert
        assertEquals(
                "Login successful",
                response.getMessage());

        assertEquals(
                "jwt-token",
                response.getToken());

        verify(jwtService)
                .generateToken(user);

    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        UserRepository repository =
                mock(UserRepository.class);

        PasswordEncoder encoder =
                mock(PasswordEncoder.class);

        JwtService jwtService =
                mock(JwtService.class);

        AuthService service =
                new AuthService(
                        repository,
                        encoder,
                        jwtService
                );

        when(repository.findByEmail(any()))
                .thenReturn(Optional.empty());

        assertThrows(

                UserNotFoundException.class,

                () -> service.login(

                        new LoginRequest(
                                "abc@gmail.com",
                                "123456"
                        )
                )
        );

    }

}
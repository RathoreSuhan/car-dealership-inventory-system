package com.incubyte.backend.security;

import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.repository.UserRepository;
import com.incubyte.backend.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CustomUserDetailsService.
 */
class CustomUserDetailsServiceTest {

    @Test
    void shouldLoadUserByUsername() {

        // Arrange
        UserRepository repository =
                mock(UserRepository.class);

        CustomUserDetailsService service =
                new CustomUserDetailsService(repository);

        User user = User.builder()
                .id(1L)
                .name("Suhan")
                .email("suhan@gmail.com")
                .password("encoded-password")
                .role("USER")
                .build();

        when(repository.findByEmail("suhan@gmail.com"))
                .thenReturn(Optional.of(user));

        // Act
        User loadedUser =
                (User) service.loadUserByUsername("suhan@gmail.com");

        // Assert
        assertEquals(
                "suhan@gmail.com",
                loadedUser.getUsername());

    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        UserRepository repository =
                mock(UserRepository.class);

        CustomUserDetailsService service =
                new CustomUserDetailsService(repository);

        when(repository.findByEmail(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(

                UserNotFoundException.class,

                () -> service.loadUserByUsername(
                        "abc@gmail.com"
                )

        );

    }

}
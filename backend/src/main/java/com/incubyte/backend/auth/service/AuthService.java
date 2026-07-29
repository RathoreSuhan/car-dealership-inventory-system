package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.LoginRequest;
import com.incubyte.backend.auth.dto.LoginResponse;
import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.mapper.UserMapper;
import com.incubyte.backend.exception.EmailAlreadyExistsException;
import com.incubyte.backend.auth.repository.UserRepository;
import com.incubyte.backend.exception.InvalidPasswordException;
import com.incubyte.backend.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles authentication use cases.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * Repository dependency.
     */
    // Injected automatically by Spring
    private final UserRepository userRepository;

    // Used to hash passwords before storing them
    private final PasswordEncoder passwordEncoder;


    /**
     * Registers a new user.
     */
    public RegisterResponse register(RegisterRequest request) {

        // Business rule:
        // A user cannot register twice with the same email.
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already registered"
            );
        }

        User user = UserMapper.toUser(request);

        // Every newly registered account is a USER.
        user.setRole("USER");

        user.setPassword(

                passwordEncoder.encode(
                        request.getPassword()
                )

        );

        userRepository.save(user);

        return UserMapper.toRegisterResponse(user);

    }

    public LoginResponse login(LoginRequest request) {

        // Find user by email
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        // Compare raw password with encrypted password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidPasswordException(
                    "Invalid password");
        }

        // JWT will be added later
        return LoginResponse.builder()
                .token("TEMP_TOKEN")
                .message("Login successful")
                .build();

    }



}
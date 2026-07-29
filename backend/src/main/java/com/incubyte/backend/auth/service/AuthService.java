package com.incubyte.backend.auth.service;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;
import com.incubyte.backend.auth.mapper.UserMapper;
import com.incubyte.backend.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles authentication use cases.
 */
@Service
public class AuthService {

    /**
     * Repository dependency.
     */
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor Injection.
     */
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        User user =
                UserMapper.toUser(request);

        user.setPassword(

                passwordEncoder.encode(
                        request.getPassword()
                )

        );

        userRepository.save(user);

        return UserMapper.toRegisterResponse(user);

    }



}
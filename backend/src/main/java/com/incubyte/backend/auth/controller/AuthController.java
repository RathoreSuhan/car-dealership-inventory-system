package com.incubyte.backend.auth.controller;

import com.incubyte.backend.auth.dto.LoginRequest;
import com.incubyte.backend.auth.dto.LoginResponse;
import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles authentication APIs.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * Business logic layer.
     */
    private final AuthService authService;

    /**
     * Register new user.
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(

            @Valid
            @RequestBody RegisterRequest request

    ) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity

                .status(HttpStatus.CREATED)

                .body(response);

    }

    /**
     * Login existing user.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

            @Valid
            @RequestBody LoginRequest request

    ) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);

    }

}
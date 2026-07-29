package com.incubyte.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request received while logging in.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    // User email
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    // Raw password entered by user
    @NotBlank(message = "Password is required")
    private String password;

}
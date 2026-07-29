package com.incubyte.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents the incoming request for user registration.
 * This class is only responsible for carrying data from the client to the backend.
 * It does NOT contain any business logic.
 */
public class RegisterRequest {

    /**
     * Full name of the user.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Email used for login.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    /**
     * Plain text password received from the client.
     *
     * It will later be encrypted before storing
     * into the database.
     */
    @NotBlank(message = "Password is required")
    private String password;

    // Default constructor required by Jackson
    public RegisterRequest() {
    }

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
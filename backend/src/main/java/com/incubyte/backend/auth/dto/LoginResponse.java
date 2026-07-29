package com.incubyte.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Response returned after successful login.
 */
@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String message;

}
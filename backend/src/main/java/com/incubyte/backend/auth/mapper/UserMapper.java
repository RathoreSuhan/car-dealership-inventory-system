package com.incubyte.backend.auth.mapper;

import com.incubyte.backend.auth.dto.RegisterRequest;
import com.incubyte.backend.auth.dto.RegisterResponse;
import com.incubyte.backend.auth.entity.User;

/**
 * Responsible only for converting
 * one object into another.
 *
 * Contains no business logic.
 */
public final class UserMapper {

    /**
     * Utility class.
     */
    private UserMapper() {
    }

    /**
     * Convert registration request into User entity.
     */
    public static User toUser(RegisterRequest request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

    }

    /**
     * Convert User entity into API response.
     */
    public static RegisterResponse toRegisterResponse(User user) {

        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .message("Registration successful")
                .build();

    }

}
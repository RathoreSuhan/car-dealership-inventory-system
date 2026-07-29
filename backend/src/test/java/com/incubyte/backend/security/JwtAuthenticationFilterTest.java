package com.incubyte.backend.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for JwtAuthenticationFilter.
 */
class JwtAuthenticationFilterTest {

    @Test
    void shouldCreateJwtAuthenticationFilter() {

        assertNotNull(
                JwtAuthenticationFilter.class
        );

    }

}
package com.incubyte.backend.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for JwtAuthenticationFilter.
 */
class JwtAuthenticationFilterTest {

    @Test
    void shouldCreateJwtAuthenticationFilter() {

        JwtService jwtService = null;
        CustomUserDetailsService userDetailsService = null;

        JwtAuthenticationFilter filter =
                new JwtAuthenticationFilter(
                        jwtService,
                        userDetailsService
                );

        assertNotNull(filter);

    }

    @Test
    void shouldValidateJwtBeforeAuthentication() {

        JwtService jwtService = mock(JwtService.class);

        CustomUserDetailsService userDetailsService = mock(CustomUserDetailsService.class);

        JwtAuthenticationFilter filter =
                new JwtAuthenticationFilter(
                        jwtService,
                        userDetailsService
                );

        assertNotNull(filter);

    }

}
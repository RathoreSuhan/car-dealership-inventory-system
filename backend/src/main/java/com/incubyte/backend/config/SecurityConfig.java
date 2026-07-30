package com.incubyte.backend.config;

import com.incubyte.backend.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configures Spring Security for the application.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * JWT authentication filter executed before
     * UsernamePasswordAuthenticationFilter.
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Defines application security rules.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .cors(cors -> {})

                // Disable CSRF for REST APIs.
                .csrf(csrf -> csrf.disable())

                // JWT is stateless.
                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Authorization rules.
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**"
                        )
                        .permitAll()

                        // -----------------------------
                        // ADMIN ONLY
                        // -----------------------------

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/vehicles/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/vehicles/*/restock"
                        )
                        .hasRole("ADMIN")

                        // -----------------------------
                        // AUTHENTICATED USERS
                        // -----------------------------

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/vehicles/**"
                        )
                        .authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/vehicles/*/purchase"
                        )
                        .authenticated()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/vehicles"
                        )
                        .authenticated()

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/vehicles/**"
                        )
                        .authenticated()

                        // Everything else
                        .anyRequest()
                        .authenticated()

                )

                // Execute JWT filter before Spring's authentication filter.
                .addFilterBefore(

                        jwtAuthenticationFilter,

                        UsernamePasswordAuthenticationFilter.class

                );

        return http.build();

    }

    /**
     * Exposes AuthenticationManager as a Spring Bean.
     *
     * Required later during user login authentication.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
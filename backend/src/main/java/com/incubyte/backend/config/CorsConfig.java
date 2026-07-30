package com.incubyte.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // React frontend URL
        configuration.setAllowedOrigins(

                List.of("http://localhost:5173")

        );

        // Allowed HTTP methods
        configuration.setAllowedMethods(

                List.of(

                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"

                )

        );

        // Allow all request headers
        configuration.setAllowedHeaders(

                List.of("*")

        );

        // Allow JWT Authorization header
        configuration.setExposedHeaders(

                List.of("Authorization")

        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(

                "/**",

                configuration

        );

        return source;

    }

}
package com.incubyte.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Defines reusable Spring Beans.
 */
@Configuration
public class SecurityBeansConfig {

    /**
     * BCrypt is the recommended password hashing algorithm provided by Spring Security.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
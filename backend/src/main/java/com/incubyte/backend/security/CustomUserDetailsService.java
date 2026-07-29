package com.incubyte.backend.security;

import com.incubyte.backend.auth.repository.UserRepository;
import com.incubyte.backend.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Loads users from the database for Spring Security.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    /**
     * Repository used to retrieve users.
     */
    private final UserRepository userRepository;

    /**
     * Spring Security calls this method whenever
     * authentication is required.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        return userRepository

                .findByEmail(username)

                .orElseThrow(

                        () -> new UserNotFoundException(

                                "User not found"

                        )

                );

    }

}
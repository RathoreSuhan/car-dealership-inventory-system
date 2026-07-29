package com.incubyte.backend.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter responsible for authenticating every
 * incoming HTTP request using JWT.
 *
 * JWT validation logic will be added in the next step.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Generates and validates JWT tokens.
     */
    private final JwtService jwtService;

    /**
     * Loads users from PostgreSQL.
     */
    private final CustomUserDetailsService userDetailsService;


    /**
     * Executes once for every request.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Read Authorization header.
        final String authHeader = request.getHeader("Authorization");

        // No JWT present → continue request.
        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;

        }

        // Extract JWT token.
        String jwt = authHeader.substring(7);

        // Username extraction will be implemented next.
        String username = jwtService.extractUsername(jwt);

        // Authenticate only if not already authenticated.
        if (username != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(

                            userDetails,

                            null,

                            userDetails.getAuthorities()

                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authToken);

        }

        // Continue request.
        filterChain.doFilter(request, response);

    }

}
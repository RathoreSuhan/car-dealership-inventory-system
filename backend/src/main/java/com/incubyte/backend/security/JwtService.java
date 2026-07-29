package com.incubyte.backend.security;

import com.incubyte.backend.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Handles JWT generation.
 *
 * Validation methods will be added later.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Generates JWT for authenticated user.
     */
    public String generateToken(UserDetails userDetails) {

        SecretKey key =
                Keys.hmacShaKeyFor(
                        jwtSecret.getBytes(
                                StandardCharsets.UTF_8));

        return Jwts.builder()

                // Email becomes the JWT subject.
                .subject(userDetails.getUsername())

                // Store user's role inside JWT claims.
                .claim(

                        "role",

                        userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()

                )

                // issue time
                .issuedAt(new Date())

                // expiry time
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpiration))

                // sign token
                .signWith(key)

                .compact();

    }

}
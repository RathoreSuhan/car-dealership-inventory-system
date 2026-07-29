package com.incubyte.backend.security;

import com.incubyte.backend.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
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

        SecretKey key = getSigningKey();

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

    /**
     * Returns username(email) stored inside JWT.
     */
    public String extractUsername(String token){
        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Checks whether the JWT has expired.
     */
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)

                .getExpiration()

                .before(new Date());

    }

    /**
     * Validates the JWT against the authenticated user.
     */
    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        String username =
                extractUsername(token);

        return username.equals(
                userDetails.getUsername())

                &&

                !isTokenExpired(token);

    }


    /**
     * Creates signing key from configured secret.
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                jwtSecret.getBytes(
                        StandardCharsets.UTF_8
                )
        );

    }

    /**
     * Extracts all JWT claims.
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

}
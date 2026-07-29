package com.incubyte.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Handles application-wide exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleDuplicateEmail(
            EmailAlreadyExistsException ex
    ) {

        return ResponseEntity

                .status(HttpStatus.CONFLICT)

                .body(

                        Map.of(

                                "timestamp", LocalDateTime.now(),

                                "message", ex.getMessage()

                        )

                );

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(
            UserNotFoundException ex
    ) {

        return ResponseEntity

                .status(HttpStatus.NOT_FOUND)

                .body(

                        Map.of(

                                "timestamp", LocalDateTime.now(),

                                "message", ex.getMessage()

                        )

                );

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handlePassword(
            InvalidPasswordException ex
    ) {

        return ResponseEntity

                .status(HttpStatus.UNAUTHORIZED)

                .body(

                        Map.of(

                                "timestamp", LocalDateTime.now(),

                                "message", ex.getMessage()

                        )

                );

    }

}
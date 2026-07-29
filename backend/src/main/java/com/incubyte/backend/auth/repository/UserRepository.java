package com.incubyte.backend.auth.repository;

import com.incubyte.backend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User persistence.
 *
 * Spring Data JPA automatically generates the implementation
 * for derived query methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Returns true if a user already exists
     * with the given email.
     */
    boolean existsByEmail(String email);

}
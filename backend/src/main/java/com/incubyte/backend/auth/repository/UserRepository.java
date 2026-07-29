package com.incubyte.backend.auth.repository;

import com.incubyte.backend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
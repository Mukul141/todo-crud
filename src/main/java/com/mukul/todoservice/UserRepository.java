package com.mukul.todoservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // We will need this method to find a user by their username during the login process.
    Optional<User> findByUsername(String username);
}
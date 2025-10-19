package com.example.User.Registration.Login.System;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //gives us basic CRUD operations
    Optional<User> findByEmail(String email); //custom method to find user by email, it returns an Optional<User> because the user might or might not exist
}
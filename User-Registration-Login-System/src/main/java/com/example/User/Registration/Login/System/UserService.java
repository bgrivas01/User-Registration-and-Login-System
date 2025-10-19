package com.example.User.Registration.Login.System;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) { //a method used to register a new user, all it really does is encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword())); //encodes password
        return userRepository.save(user); //saves info of user to database
    }

    public User findByEmail(String email) {//method used to find a user by their email
        return userRepository.findByEmail(email).orElse(null);
    }
}
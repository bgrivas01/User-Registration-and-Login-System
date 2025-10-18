package com.example.User.Registration.Login.System;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor //creates contructors for our "final" fields
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register") //add a user with there info to our database(basically we are registering a new account)
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    
    @PostMapping("/login") //this basically checks if our user trying to log in is valid, it doesnt actually log them in 
    public String login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            return "Login successful for user: " + authentication.getName();
        } catch (AuthenticationException e) {
            return "Invalid credentials!";
        }
    }
}

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

    
  @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    String token = userService.loginUser(user.getEmail(), user.getPassword());
    if (token != null) {
        return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }
}
}

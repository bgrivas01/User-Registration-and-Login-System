package com.example.User.Registration.Login.System;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //marks a class as a source of bean definitions
@RequiredArgsConstructor //creates contructor for all my "final feilds"
public class SecurityConfig {

    //PasswordEncoder is a interface used to hash passwords
    private final PasswordEncoder passwordEncoder; //since we have @RequiredArgsContructor we dont need to make a contructor for this

    //code below defines all security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        http
            .csrf(csrf -> csrf.disable()) //this disables csrf(Cross-Sight-Request-Forgery) because we will use tokens to protect us unstead 
            .authorizeHttpRequests(auth -> auth  //with this and the following code we will decide who can see what
                    .requestMatchers("/api/auth/**").permitAll() //this says that any request that contains "/api/auth/" is open to anyone to see
                    .anyRequest().authenticated() //this says that if you want to see anything else you have to be authorized(logged in) to do so
            )
            .formLogin(login -> login.permitAll());  //this means anyone can use the login form that springSecurity provides
        return http.build();  //builds our securityConfig and gives it to spring during runtime
    }

    //the code below is what is responisble for authenticating users(verifying there credentials)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}


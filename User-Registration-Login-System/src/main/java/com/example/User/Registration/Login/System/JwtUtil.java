package com.example.User.Registration.Login.System.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); //the secret key used to sign tokens

    // 24 hours
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; //will "log out after 24 hrs"

    //code below creates a token containing users email 
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) //gives the toekn the email
                .setIssuedAt(new Date()) //the time the token was created
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //setting when the token will expire
                .signWith(secretKey) //signs our token with our secret key
                .compact(); //compacts all the prevous info into one string
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.SpringSecurity.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringSecurity.authservice.dto.AuthResponse;
import com.SpringSecurity.authservice.entity.User;
import com.SpringSecurity.authservice.repository.UserRepo;
import com.SpringSecurity.authservice.security.JwtUtil;

@Service    
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER METHOD (UPDATED)
    public String register(User user) {

        if (user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        // encode password
        user.setPassword(encoder.encode(user.getPassword()));

        // ✅ IMPORTANT FIX: set default role and normalize to uppercase
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        } else {
            user.setRole(user.getRole().toUpperCase());
        }

        if (repo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // save user
        repo.save(user);

        System.out.println("[AUTH SERVICE] User registered: " + user.getUsername());

        return "User Registered Successfully";
    }

    // ✅ LOGIN METHOD (NO CHANGE NEEDED)
    public AuthResponse login(User user) {
        try {

            if (user.getUsername() == null || user.getPassword() == null) {
                return new AuthResponse("Username and password are required", false);
            }

            System.out.println("[AUTH SERVICE] Login attempt for user: " + user.getUsername());

            User dbUser = repo.findByUsername(user.getUsername()).orElse(null);

            if (dbUser == null) {
                System.out.println("[AUTH SERVICE] User not found: " + user.getUsername());
                return new AuthResponse("User not found", false);
            }

            if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
                System.out.println("[AUTH SERVICE] Invalid password for user: " + user.getUsername());
                return new AuthResponse("Invalid Password", false);
            }

            // generate token
            String token = jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole());

            System.out.println("[AUTH SERVICE] Login successful, token generated for: " + dbUser.getUsername());

            return new AuthResponse("Login successful", token, true);

        } catch (Exception e) {
            System.err.println("[AUTH SERVICE] Login error: " + e.getMessage());
            e.printStackTrace();
            return new AuthResponse("Login failed: " + e.getMessage(), false);
        }
    }
}
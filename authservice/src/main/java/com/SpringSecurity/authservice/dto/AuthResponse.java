package com.SpringSecurity.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String token;
    private boolean success;

    public AuthResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
        this.token = null;
    }
}

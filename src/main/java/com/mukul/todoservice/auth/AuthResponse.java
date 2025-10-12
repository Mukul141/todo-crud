package com.mukul.todoservice.auth;

// DTO for the authentication response (returning the JWT)
public record AuthResponse(String token) {
}
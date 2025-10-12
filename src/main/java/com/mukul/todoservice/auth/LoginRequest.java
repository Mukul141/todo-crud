package com.mukul.todoservice.auth;

// DTO for user login request
public record LoginRequest(String username, String password) {
}
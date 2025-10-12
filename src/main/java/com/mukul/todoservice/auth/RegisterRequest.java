package com.mukul.todoservice.auth;

// DTO for user registration request
public record RegisterRequest(String username, String password) {
}
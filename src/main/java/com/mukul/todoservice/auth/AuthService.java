package com.mukul.todoservice.auth;

import com.mukul.todoservice.User;
import com.mukul.todoservice.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Logic for user registration
    public AuthResponse register(RegisterRequest request) {
        // Create a new user object
        User user = new User();
        user.setUsername(request.username());
        // IMPORTANT: Always encode the password before saving!
        user.setPassword(passwordEncoder.encode(request.password()));

        // Save the new user to the database
        userRepository.save(user);

        // Generate a JWT for the new user
        String jwtToken = jwtService.generateToken(user);

        // Return the token in the response
        return new AuthResponse(jwtToken);
    }

    // Logic for user login
    public AuthResponse login(LoginRequest request) {
        // Let Spring Security handle the authentication
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        // If authentication was successful, find the user
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(); // Should not happen if auth succeeded

        // Generate a JWT for the authenticated user
        String jwtToken = jwtService.generateToken(user);

        // Return the token in the response
        return new AuthResponse(jwtToken);
    }
}
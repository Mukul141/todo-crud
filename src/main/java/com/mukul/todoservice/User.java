package com.mukul.todoservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "app_users") // "user" is often a reserved keyword in SQL, so we use a different name
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    private String password;

    // --- Constructors, Getters, and Setters ---
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // --- UserDetails interface methods ---

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // For simplicity, we'll keep these hardcoded for now.
    // This means all users are active and have basic permissions.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // We will deal with Roles later. For now, an empty list is fine.
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
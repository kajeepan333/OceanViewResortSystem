package com.oceanview.model;

import java.time.LocalDate;

/**
 * Model class representing a User
 * Part of MVC Pattern - Model component
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String createdAt;

    public User() {}

    public User(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Check if user is admin
     * @return true if user has ADMIN role
     */
    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }

    /**
     * Check if user is staff
     * @return true if user has STAFF role
     */
    public boolean isStaff() {
        return "STAFF".equals(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

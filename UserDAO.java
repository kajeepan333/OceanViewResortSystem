package com.oceanview.dao;

import com.oceanview.model.User;
import com.oceanview.util.DBConnection;
import com.oceanview.util.PasswordUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO Pattern Implementation
 * Data Access Object for User operations
 * Encapsulates all database operations for User entity
 */
public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Authenticate user with username and password
     * @param username Username
     * @param password Plain text password
     * @return User object if authentication successful, null otherwise
     */
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (PasswordUtil.verifyPassword(password, hashedPassword)) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Create new user
     * @param user User object
     * @return true if user created successfully
     */
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, role, email) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, PasswordUtil.hashPassword(user.getPassword()));
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get user by username
     * @param username Username
     * @return User object if found, null otherwise
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting user: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Get user by email
     * @param email Email address
     * @return User object if found, null otherwise
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting user by email: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Update user password
     * @param username Username
     * @param newPassword New plain text password
     * @return true if password updated successfully
     */
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, PasswordUtil.hashPassword(newPassword));
            stmt.setString(2, username);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if username exists
     * @param username Username
     * @return true if username exists
     */
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Check if email exists
     * @param email Email address
     * @return true if email exists
     */
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Get all users (admin only)
     * @return List of all users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY created_at DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all users: " + e.getMessage());
        }
        
        return users;
    }

    /**
     * Map ResultSet to User object
     * @param rs ResultSet
     * @return User object
     * @throws SQLException
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setEmail(rs.getString("email"));
        user.setCreatedAt(rs.getString("created_at"));
        return user;
    }
}

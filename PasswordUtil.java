package com.oceanview.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Utility class for password operations
 * Includes password hashing and validation
 */
public class PasswordUtil {

    /**
     * Hash password using SHA-256
     * @param password Plain text password
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Validate password according to requirements
     * @param password Password to validate
     * @return true if password meets all requirements
     */
    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        // At least 1 uppercase
        Pattern uppercase = Pattern.compile("[A-Z]");
        // At least 1 lowercase
        Pattern lowercase = Pattern.compile("[a-z]");
        // At least 1 number
        Pattern number = Pattern.compile("[0-9]");
        // At least 1 special character
        Pattern special = Pattern.compile("[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]");
        
        return uppercase.matcher(password).find() &&
               lowercase.matcher(password).find() &&
               number.matcher(password).find() &&
               special.matcher(password).find();
    }

    /**
     * Get password validation error message
     * @param password Password to validate
     * @return Error message or empty string if valid
     */
    public static String getPasswordValidationError(String password) {
        if (password == null) {
            return "Password cannot be null";
        }
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }
        
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*");
        
        StringBuilder errors = new StringBuilder();
        if (!hasUppercase) errors.append("At least 1 uppercase letter required. ");
        if (!hasLowercase) errors.append("At least 1 lowercase letter required. ");
        if (!hasNumber) errors.append("At least 1 number required. ");
        if (!hasSpecial) errors.append("At least 1 special character required. ");
        
        return errors.toString().trim();
    }

    /**
     * Verify password against hash
     * @param password Plain text password
     * @param hashedPassword Hashed password from database
     * @return true if password matches
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }
}

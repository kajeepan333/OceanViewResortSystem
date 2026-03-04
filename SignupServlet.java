package com.oceanview.controller;

import com.oceanview.dao.UserDAO;
import com.oceanview.model.User;
import com.oceanview.util.PasswordUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Signup Controller
 * Handles staff user registration
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/signup")
public class SignupServlet extends BaseServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String method = request.getMethod();

        if ("GET".equals(method)) {
            showSignupPage(request, response);
        } else if ("POST".equals(method)) {
            processSignup(request, response);
        }
    }

    private void showSignupPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "signup.jsp");
    }

    private void processSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        // Validate inputs
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            showSignupPage(request, response);
            return;
        }

        // Validate password match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            showSignupPage(request, response);
            return;
        }

        // Validate password strength
        if (!PasswordUtil.validatePassword(password)) {
            request.setAttribute("errorMessage", PasswordUtil.getPasswordValidationError(password));
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            showSignupPage(request, response);
            return;
        }

        // Check if username already exists
        if (userDAO.usernameExists(username.trim())) {
            request.setAttribute("errorMessage", "Username already exists.");
            request.setAttribute("email", email);
            showSignupPage(request, response);
            return;
        }

        // Check if email already exists
        if (userDAO.emailExists(email.trim())) {
            request.setAttribute("errorMessage", "Email already registered.");
            request.setAttribute("username", username);
            showSignupPage(request, response);
            return;
        }

        // Create new staff user
        User newUser = new User(username.trim(), password, "STAFF", email.trim());
        boolean success = userDAO.createUser(newUser);

        if (success) {
            request.setAttribute("successMessage", 
                "Account created successfully! You can now login.");
            forwardToJsp(request, response, "login.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to create account. Please try again.");
            showSignupPage(request, response);
        }
    }
}

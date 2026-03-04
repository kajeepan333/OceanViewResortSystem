package com.oceanview.controller;

import com.oceanview.dao.UserDAO;
import com.oceanview.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login Controller
 * Handles user authentication
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        
        String method = request.getMethod();
        
        if ("GET".equals(method)) {
            // Show login page
            showLoginPage(request, response);
        } else if ("POST".equals(method)) {
            // Process login
            processLogin(request, response);
        }
    }

    /**
     * Show login page
     */
    private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        forwardToJsp(request, response, "login.jsp");
    }

    /**
     * Process login form submission
     */
    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            setErrorMessage(request, "Username and password are required");
            showLoginPage(request, response);
            return;
        }
        
        // Authenticate user
        User user = userDAO.authenticate(username.trim(), password);
        
        if (user != null) {
            // Create session
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            
            // Redirect to appropriate dashboard
            // Redirect to dashboard servlet which handles role-based routing
            redirectToUrl(response, request.getContextPath() + "/controller/dashboard");
        } else {
            // Authentication failed
            setErrorMessage(request, "Invalid username or password");
            showLoginPage(request, response);
        }
    }
}

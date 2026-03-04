package com.oceanview.controller;

import com.oceanview.dao.UserDAO;
import com.oceanview.model.User;
import com.oceanview.util.EmailUtil;
import com.oceanview.util.PasswordUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Forgot Password Controller
 * Handles password reset requests via email
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends BaseServlet {

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
            showForgotPasswordPage(request, response);
        } else if ("POST".equals(method)) {
            processForgotPassword(request, response);
        }
    }

    private void showForgotPasswordPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "forgotPassword.jsp");
    }

    private void processForgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please enter your email address.");
            showForgotPasswordPage(request, response);
            return;
        }

        User user = userDAO.getUserByEmail(email.trim());

        if (user != null) {
            // Generate new temporary password
            String newPassword = EmailUtil.generateRandomPassword();
            
            // Update password in database
            boolean updated = userDAO.updatePassword(user.getUsername(), newPassword);
            
            if (updated) {
                // Try to send email
                boolean emailSent = EmailUtil.sendPasswordResetEmail(email.trim(), newPassword);
                
                if (emailSent) {
                    request.setAttribute("successMessage", 
                        "A new password has been sent to your email address.");
                } else {
                    // Email failed but password was reset — show the new password on screen
                    request.setAttribute("successMessage", 
                        "Your password has been reset. New password: " + newPassword + 
                        " (Email delivery failed — please save this password)");
                }
            } else {
                request.setAttribute("errorMessage", "Failed to reset password. Please try again.");
            }
        } else {
            request.setAttribute("errorMessage", "No account found with that email address.");
        }

        showForgotPasswordPage(request, response);
    }
}

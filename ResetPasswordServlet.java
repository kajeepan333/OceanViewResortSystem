package com.oceanview.controller;

import com.oceanview.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Reset Password Controller
 * Handles password reset with token-based or direct reset
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/resetPassword")
public class ResetPasswordServlet extends BaseServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Reset password is handled via ForgotPasswordServlet flow
        redirectToUrl(response, request.getContextPath() + "/controller/forgotPassword");
    }
}

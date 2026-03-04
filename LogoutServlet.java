package com.oceanview.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Logout Controller
 * Handles user logout by invalidating the session
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        redirectToUrl(response, request.getContextPath() + "/controller/login");
    }
}

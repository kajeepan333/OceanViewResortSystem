package com.oceanview.controller;

import com.oceanview.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Dashboard Controller
 * Redirects users to the appropriate dashboard based on their role
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/dashboard")
public class DashboardServlet extends BaseServlet {

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = getCurrentUser(request);
        
        if (user == null) {
            redirectToUrl(response, request.getContextPath() + "/controller/login");
            return;
        }
        
        if (user.isAdmin()) {
            forwardToJsp(request, response, "admin/dashboard.jsp");
        } else {
            forwardToJsp(request, response, "staff/dashboard.jsp");
        }
    }
}

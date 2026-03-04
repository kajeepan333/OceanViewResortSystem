package com.oceanview.controller;

import com.oceanview.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Template Method Pattern Implementation
 * Base servlet class that provides common functionality
 * Defines the template for request processing
 */
public abstract class BaseServlet extends HttpServlet {

    /**
     * Template method for processing requests
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException
     */
    protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Common preprocessing
        preprocessRequest(request, response);
        
        // Execute specific logic (implemented by subclasses)
        executeLogic(request, response);
        
        // Common postprocessing
        postprocessRequest(request, response);
    }

    /**
     * Common preprocessing for all requests
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException
     */
    protected void preprocessRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set common attributes
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                request.setAttribute("currentUser", user);
                request.setAttribute("isAdmin", user.isAdmin());
                request.setAttribute("isStaff", user.isStaff());
            }
        }
        
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Abstract method to be implemented by subclasses
     * Contains the specific business logic for each servlet
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * Common postprocessing for all requests
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException
     */
    protected void postprocessRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Default implementation - can be overridden by subclasses
    }

    /**
     * Forward request to JSP page
     * @param request HTTP request
     * @param response HTTP response
     * @param jspPath JSP file path
     * @throws ServletException
     * @throws IOException
     */
    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/" + jspPath).forward(request, response);
    }

    /**
     * Redirect to URL
     * @param response HTTP response
     * @param url Target URL
     * @throws IOException
     */
    protected void redirectToUrl(HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(url);
    }

    /**
     * Get current user from session
     * @param request HTTP request
     * @return Current user or null
     */
    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (User) session.getAttribute("user") : null;
    }

    /**
     * Check if current user is admin
     * @param request HTTP request
     * @return true if current user is admin
     */
    protected boolean isCurrentUserAdmin(HttpServletRequest request) {
        User user = getCurrentUser(request);
        return user != null && user.isAdmin();
    }

    /**
     * Check if current user is staff
     * @param request HTTP request
     * @return true if current user is staff
     */
    protected boolean isCurrentUserStaff(HttpServletRequest request) {
        User user = getCurrentUser(request);
        return user != null && user.isStaff();
    }

    /**
     * Set error message in request
     * @param request HTTP request
     * @param message Error message
     */
    protected void setErrorMessage(HttpServletRequest request, String message) {
        request.setAttribute("errorMessage", message);
    }

    /**
     * Set success message in request
     * @param request HTTP request
     * @param message Success message
     */
    protected void setSuccessMessage(HttpServletRequest request, String message) {
        request.setAttribute("successMessage", message);
    }

    /**
     * Get parameter as integer with default value
     * @param request HTTP request
     * @param paramName Parameter name
     * @param defaultValue Default value
     * @return Integer value
     */
    protected int getIntParameter(HttpServletRequest request, String paramName, int defaultValue) {
        String paramValue = request.getParameter(paramName);
        if (paramValue != null && !paramValue.trim().isEmpty()) {
            try {
                return Integer.parseInt(paramValue.trim());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

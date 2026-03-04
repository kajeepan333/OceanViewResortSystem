package com.oceanview.filter;

import com.oceanview.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Role-Based Authorization Filter
 * Implements security checks for different user roles
 * Part of the Role-Based Authorization Pattern
 */
@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    // Paths that don't require authentication
    private static final String[] PUBLIC_PATHS = {
        "/login", "/signup", "/forgotPassword", "/resetPassword",
        "/controller/login", "/controller/signup", "/controller/forgotPassword", "/controller/resetPassword",
        "/assets/", "/css/", "/js/", "/images/"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Authorization Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        
        // Check if path is public
        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check if user is authenticated
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
        // Role-based access control
        if (!hasAccess(user, path)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/accessDenied");
            return;
        }
        
        // Set user information in request for JSP access
        httpRequest.setAttribute("currentUser", user);
        httpRequest.setAttribute("isAdmin", user.isAdmin());
        httpRequest.setAttribute("isStaff", user.isStaff());
        
        chain.doFilter(request, response);
    }

    /**
     * Check if path is public (doesn't require authentication)
     * @param path Request path
     * @return true if path is public
     */
    private boolean isPublicPath(String path) {
        for (String publicPath : PUBLIC_PATHS) {
            if (path.equals(publicPath) || path.startsWith(publicPath)) {
                return true;
            }
        }
        return path.equals("/") || path.isEmpty();
    }

    /**
     * Check if user has access to the requested path
     * @param user User object
     * @param path Request path
     * @return true if user has access
     */
    private boolean hasAccess(User user, String path) {
        // Admin can access everything
        if (user.isAdmin()) {
            return true;
        }
        
        // Staff access restrictions
        if (user.isStaff()) {
            // Staff cannot access admin-only pages
            if (path.contains("/admin/") || path.contains("delete") || path.contains("edit")) {
                return false;
            }
            return true;
        }
        
        return false;
    }

    @Override
    public void destroy() {
        System.out.println("Authorization Filter destroyed");
    }
}

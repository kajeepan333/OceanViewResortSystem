package com.oceanview.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front Controller Pattern Implementation
 * Main controller that handles all requests and delegates to appropriate handlers
 * Provides a single entry point for all web requests
 */
@WebServlet("/controller/*")
public class FrontController extends BaseServlet {

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            redirectToUrl(response, request.getContextPath() + "/controller/login");
            return;
        }
        
        // Route to appropriate handler based on path
        switch (pathInfo) {
            case "/login":
                forward(request, response, "/login");
                break;
            case "/logout":
                forward(request, response, "/logout");
                break;
            case "/dashboard":
                forward(request, response, "/dashboard");
                break;
            case "/addReservation":
                forwardToJsp(request, response, "addReservation.jsp");
                break;
            case "/viewReservations":
                forward(request, response, "/viewReservations");
                break;
            case "/editReservation":
                forward(request, response, "/editReservation");
                break;
            case "/deleteReservation":
                forward(request, response, "/deleteReservation");
                break;
            case "/saveReservation":
                forward(request, response, "/saveReservation");
                break;
            case "/generateBill":
                forward(request, response, "/generateBill");
                break;
            case "/forgotPassword":
                forward(request, response, "/forgotPassword");
                break;
            case "/resetPassword":
                forward(request, response, "/resetPassword");
                break;
            case "/signup":
                forward(request, response, "/signup");
                break;
            case "/help":
                forward(request, response, "/help");
                break;
            case "/accessDenied":
                forwardToJsp(request, response, "accessDenied.jsp");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    /**
     * Forward request to the specified path
     * @param request HTTP request
     * @param response HTTP response
     * @param path Target path
     * @throws ServletException
     * @throws IOException
     */
    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}

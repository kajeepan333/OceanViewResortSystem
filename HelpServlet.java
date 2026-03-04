package com.oceanview.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Help Controller
 * Displays the help and guide page
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/help")
public class HelpServlet extends BaseServlet {

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "help.jsp");
    }
}

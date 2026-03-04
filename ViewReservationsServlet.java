package com.oceanview.controller;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * View Reservations Controller
 * Handles listing and searching reservations
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/viewReservations")
public class ViewReservationsServlet extends BaseServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchQuery = request.getParameter("search");
        List<Reservation> reservations;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            reservations = reservationDAO.searchReservationsByGuestName(searchQuery.trim());
            request.setAttribute("searchQuery", searchQuery.trim());
        } else {
            reservations = reservationDAO.getAllReservations();
        }

        // Check for success/error messages from session (set after redirect)
        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
        }
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getSession().removeAttribute("errorMessage");
        }

        request.setAttribute("reservations", reservations);
        forwardToJsp(request, response, "viewReservations.jsp");
    }
}

package com.oceanview.controller;

import com.oceanview.dao.ReservationDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Delete Reservation Controller
 * Handles deleting reservations (Admin only)
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/deleteReservation")
public class DeleteReservationServlet extends BaseServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Only admins can delete
        if (!isCurrentUserAdmin(request)) {
            redirectToUrl(response, request.getContextPath() + "/controller/accessDenied");
            return;
        }

        int id = getIntParameter(request, "id", -1);
        
        if (id == -1) {
            request.getSession().setAttribute("errorMessage", "Invalid reservation ID.");
            redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
            return;
        }

        boolean success = reservationDAO.deleteReservation(id);

        if (success) {
            request.getSession().setAttribute("successMessage", "Reservation deleted successfully!");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to delete reservation.");
        }

        redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
    }
}

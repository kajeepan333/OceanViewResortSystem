package com.oceanview.controller;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Edit Reservation Controller
 * Handles editing existing reservations (Admin only)
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/editReservation")
public class EditReservationServlet extends BaseServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Only admins can edit
        if (!isCurrentUserAdmin(request)) {
            redirectToUrl(response, request.getContextPath() + "/controller/accessDenied");
            return;
        }

        String method = request.getMethod();

        if ("GET".equals(method)) {
            showEditForm(request, response);
        } else if ("POST".equals(method)) {
            processEdit(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = getIntParameter(request, "id", -1);
        if (id == -1) {
            redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
            return;
        }

        Reservation reservation = reservationDAO.getReservationById(id);
        if (reservation == null) {
            request.getSession().setAttribute("errorMessage", "Reservation not found.");
            redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
            return;
        }

        request.setAttribute("reservation", reservation);
        forwardToJsp(request, response, "editReservation.jsp");
    }

    private void processEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int id = getIntParameter(request, "id", -1);
            if (id == -1) {
                redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
                return;
            }

            Reservation reservation = reservationDAO.getReservationById(id);
            if (reservation == null) {
                request.getSession().setAttribute("errorMessage", "Reservation not found.");
                redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
                return;
            }

            // Update fields
            reservation.setGuestName(request.getParameter("guestName"));
            reservation.setNicPassport(request.getParameter("nicPassport"));
            reservation.setAddress(request.getParameter("address"));
            reservation.setPhone(request.getParameter("phone"));
            reservation.setEmail(request.getParameter("email"));
            reservation.setRoomType(request.getParameter("roomType"));
            reservation.setAcType(request.getParameter("acType"));
            reservation.setAdults(getIntParameter(request, "adults", 1));
            reservation.setChildren(getIntParameter(request, "children", 0));
            reservation.setCheckIn(LocalDate.parse(request.getParameter("checkIn")));
            reservation.setCheckOut(LocalDate.parse(request.getParameter("checkOut")));
            reservation.setMealPlan(request.getParameter("mealPlan"));

            // Recalculate total using Factory
            BigDecimal roomPrice = com.oceanview.factory.RoomFactory.getRoomPrice(
                    reservation.getRoomType(), reservation.getAcType());
            long nights = java.time.temporal.ChronoUnit.DAYS.between(
                    reservation.getCheckIn(), reservation.getCheckOut());
            BigDecimal roomCost = roomPrice.multiply(BigDecimal.valueOf(nights));

            BigDecimal mealPrice = com.oceanview.factory.RoomFactory.getMealPrice(reservation.getMealPlan());
            int totalGuests = reservation.getAdults() + reservation.getChildren();
            BigDecimal mealCost = mealPrice.multiply(BigDecimal.valueOf(totalGuests))
                    .multiply(BigDecimal.valueOf(nights));

            reservation.setTotalAmount(roomCost.add(mealCost));

            boolean success = reservationDAO.updateReservation(reservation);

            if (success) {
                request.getSession().setAttribute("successMessage", "Reservation updated successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to update reservation.");
            }

            redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            showEditForm(request, response);
        }
    }
}

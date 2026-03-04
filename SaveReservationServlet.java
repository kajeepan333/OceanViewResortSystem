package com.oceanview.controller;

import com.oceanview.builder.ReservationBuilder;
import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Save Reservation Controller
 * Handles creating new reservations from form submission
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/saveReservation")
public class SaveReservationServlet extends BaseServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!"POST".equals(request.getMethod())) {
            redirectToUrl(response, request.getContextPath() + "/controller/addReservation");
            return;
        }

        try {
            // Get form parameters
            String guestName = request.getParameter("guestName");
            String nicPassport = request.getParameter("nicPassport");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String roomType = request.getParameter("roomType");
            String acType = request.getParameter("acType");
            int adults = getIntParameter(request, "adults", 1);
            int children = getIntParameter(request, "children", 0);
            String checkInStr = request.getParameter("checkIn");
            String checkOutStr = request.getParameter("checkOut");
            String mealPlan = request.getParameter("mealPlan");

            // Parse dates
            LocalDate checkIn = LocalDate.parse(checkInStr);
            LocalDate checkOut = LocalDate.parse(checkOutStr);

            // Generate unique guest ID
            String guestId = reservationDAO.generateGuestId();

            // Build reservation using Builder Pattern
            Reservation reservation = new ReservationBuilder()
                    .setGuestId(guestId)
                    .setGuestName(guestName)
                    .setNicPassport(nicPassport)
                    .setAddress(address)
                    .setPhone(phone)
                    .setEmail(email)
                    .setRoomType(roomType)
                    .setAcType(acType)
                    .setAdults(adults)
                    .setChildren(children)
                    .setCheckIn(checkIn)
                    .setCheckOut(checkOut)
                    .setMealPlan(mealPlan)
                    .calculateTotalAmount()
                    .build();

            // Save to database
            boolean success = reservationDAO.createReservation(reservation);

            if (success) {
                request.getSession().setAttribute("successMessage", 
                    "Reservation created successfully! Guest ID: " + guestId);
                redirectToUrl(response, request.getContextPath() + "/controller/viewReservations");
            } else {
                request.setAttribute("errorMessage", "Failed to create reservation. Please try again.");
                forwardToJsp(request, response, "addReservation.jsp");
            }

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            forwardToJsp(request, response, "addReservation.jsp");
        }
    }
}

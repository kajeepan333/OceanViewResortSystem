package com.oceanview.controller;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import com.oceanview.strategy.StandardBillCalculation;
import com.oceanview.util.BillPDFGenerator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Generate Bill Controller
 * Handles bill generation and PDF download
 * Extends BaseServlet using Template Method Pattern
 */
@WebServlet("/generateBill")
public class GenerateBillServlet extends BaseServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void executeLogic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String method = request.getMethod();

        if ("GET".equals(method)) {
            String guestId = request.getParameter("guestId");
            String action = request.getParameter("action");

            if ("download".equals(action) && guestId != null && !guestId.trim().isEmpty()) {
                downloadPDF(request, response, guestId.trim());
            } else if (guestId != null && !guestId.trim().isEmpty()) {
                searchReservation(request, response, guestId.trim());
            } else {
                showBillPage(request, response);
            }
        } else if ("POST".equals(method)) {
            String guestId = request.getParameter("guestId");
            if (guestId != null && !guestId.trim().isEmpty()) {
                searchReservation(request, response, guestId.trim());
            } else {
                request.setAttribute("errorMessage", "Please enter a Guest ID.");
                showBillPage(request, response);
            }
        }
    }

    private void showBillPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "generateBill.jsp");
    }

    private void searchReservation(HttpServletRequest request, HttpServletResponse response, String guestId)
            throws ServletException, IOException {
        
        Reservation reservation = reservationDAO.getReservationByGuestId(guestId);
        
        if (reservation != null) {
            request.setAttribute("reservation", reservation);
            
            // Calculate bill details using Strategy Pattern
            StandardBillCalculation calculator = new StandardBillCalculation();
            BigDecimal subtotal = reservation.getTotalAmount();
            request.setAttribute("subtotal", subtotal);
            request.setAttribute("vat", calculator.getVatAmount(subtotal));
            request.setAttribute("serviceCharge", calculator.getServiceChargeAmount(subtotal));
            request.setAttribute("grandTotal", calculator.calculateFinalAmount(subtotal));
            request.setAttribute("strategyDescription", calculator.getStrategyDescription());
        } else {
            request.setAttribute("errorMessage", "No reservation found with Guest ID: " + guestId);
        }

        showBillPage(request, response);
    }

    private void downloadPDF(HttpServletRequest request, HttpServletResponse response, String guestId)
            throws ServletException, IOException {
        
        Reservation reservation = reservationDAO.getReservationByGuestId(guestId);
        
        if (reservation != null) {
            try {
                BillPDFGenerator.generateBillPDF(reservation, response);
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Error generating PDF: " + e.getMessage());
                showBillPage(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "No reservation found with Guest ID: " + guestId);
            showBillPage(request, response);
        }
    }
}

package com.oceanview.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.oceanview.model.Reservation;
import com.oceanview.strategy.StandardBillCalculation;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * PDF Generation Utility
 * Generates professional PDF bills for reservations
 * Uses iText library for PDF creation
 */
public class BillPDFGenerator {

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

    /**
     * Generate PDF bill for reservation
     * @param reservation Reservation object
     * @param response HTTP response
     * @throws IOException
     * @throws DocumentException
     */
    public static void generateBillPDF(Reservation reservation, HttpServletResponse response)
            throws IOException, DocumentException {
        
        // Create document
        Document document = new Document(PageSize.A4);
        
        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Bill_" + reservation.getGuestId() + ".pdf\"");
        
        // Create PDF writer
        PdfWriter.getInstance(document, response.getOutputStream());
        
        // Open document
        document.open();
        
        // Add header
        addHeader(document);
        
        // Add guest information
        addGuestInformation(document, reservation);
        
        // Add reservation details
        addReservationDetails(document, reservation);
        
        // Add bill calculation
        addBillCalculation(document, reservation);
        
        // Add signatures
        addSignatures(document);
        
        // Close document
        document.close();
    }

    /**
     * Add hotel header to PDF
     */
    private static void addHeader(Document document) throws DocumentException {
        Paragraph header = new Paragraph();
        header.setAlignment(Element.ALIGN_CENTER);
        
        Chunk title = new Chunk("OCEAN VIEW RESORT", TITLE_FONT);
        header.add(title);
        header.add(Chunk.NEWLINE);
        header.add(Chunk.NEWLINE);
        
        Chunk address = new Chunk("123 Beach Road, Galle, Sri Lanka", NORMAL_FONT);
        header.add(address);
        header.add(Chunk.NEWLINE);
        
        Chunk contact = new Chunk("Tel: +94 91 123 4567 | Email: info@oceanview.com", NORMAL_FONT);
        header.add(contact);
        header.add(Chunk.NEWLINE);
        header.add(Chunk.NEWLINE);
        
        Chunk line = new Chunk("==================================================", NORMAL_FONT);
        header.add(line);
        header.add(Chunk.NEWLINE);
        header.add(Chunk.NEWLINE);
        
        document.add(header);
    }

    /**
     * Add guest information section
     */
    private static void addGuestInformation(Document document, Reservation reservation) throws DocumentException {
        Paragraph section = new Paragraph();
        section.add(new Chunk("GUEST INFORMATION", HEADER_FONT));
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 5});
        
        addTableRow(table, "Guest ID:", reservation.getGuestId(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Guest Name:", reservation.getGuestName(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "NIC/Passport:", reservation.getNicPassport(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Address:", reservation.getAddress(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Phone:", reservation.getPhone(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Email:", reservation.getEmail(), NORMAL_FONT, BOLD_FONT);
        
        section.add(table);
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        document.add(section);
    }

    /**
     * Add reservation details section
     */
    private static void addReservationDetails(Document document, Reservation reservation) throws DocumentException {
        Paragraph section = new Paragraph();
        section.add(new Chunk("RESERVATION DETAILS", HEADER_FONT));
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 5});
        
        addTableRow(table, "Room Type:", reservation.getRoomType(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "AC Type:", reservation.getAcType(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Adults:", String.valueOf(reservation.getAdults()), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Children:", String.valueOf(reservation.getChildren()), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Check-in:", reservation.getFormattedCheckIn(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Check-out:", reservation.getFormattedCheckOut(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Nights:", String.valueOf(reservation.getNumberOfNights()), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Meal Plan:", reservation.getMealPlan(), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Total Guests:", String.valueOf(reservation.getTotalGuests()), NORMAL_FONT, BOLD_FONT);
        
        section.add(table);
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        document.add(section);
    }

    /**
     * Add bill calculation section
     */
    private static void addBillCalculation(Document document, Reservation reservation) throws DocumentException {
        Paragraph section = new Paragraph();
        section.add(new Chunk("BILL CALCULATION", HEADER_FONT));
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        StandardBillCalculation calculator = new StandardBillCalculation();
        BigDecimal subtotal = reservation.getTotalAmount();
        BigDecimal vat = calculator.getVatAmount(subtotal);
        BigDecimal serviceCharge = calculator.getServiceChargeAmount(subtotal);
        BigDecimal total = calculator.calculateFinalAmount(subtotal);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{7, 3});
        
        addTableRow(table, "Room Cost (Subtotal):", formatCurrency(subtotal), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "VAT (18%):", formatCurrency(vat), NORMAL_FONT, BOLD_FONT);
        addTableRow(table, "Service Charge (5%):", formatCurrency(serviceCharge), NORMAL_FONT, BOLD_FONT);
        
        // Add separator line
        PdfPCell separatorCell = new PdfPCell(new Phrase(" ", NORMAL_FONT));
        separatorCell.setBorder(Rectangle.NO_BORDER);
        separatorCell.setColspan(2);
        table.addCell(separatorCell);
        
        // Add total
        PdfPCell totalLabel = new PdfPCell(new Phrase("GRAND TOTAL:", BOLD_FONT));
        totalLabel.setBorder(Rectangle.NO_BORDER);
        table.addCell(totalLabel);
        
        PdfPCell totalValue = new PdfPCell(new Phrase(formatCurrency(total), BOLD_FONT));
        totalValue.setBorder(Rectangle.NO_BORDER);
        table.addCell(totalValue);
        
        section.add(table);
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        document.add(section);
    }

    /**
     * Add signature section
     */
    private static void addSignatures(Document document) throws DocumentException {
        Paragraph section = new Paragraph();
        section.add(new Chunk("AUTHORIZATION", HEADER_FONT));
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 1});
        
        // Left side - Authorized Signature
        PdfPCell leftCell = new PdfPCell();
        leftCell.setBorder(Rectangle.TOP);
        leftCell.addElement(new Phrase(" ", NORMAL_FONT));
        leftCell.addElement(new Phrase("Authorized Signature", NORMAL_FONT));
        leftCell.addElement(new Phrase("(Admin/Staff)", NORMAL_FONT));
        table.addCell(leftCell);
        
        // Right side - Guest Signature
        PdfPCell rightCell = new PdfPCell();
        rightCell.setBorder(Rectangle.TOP);
        rightCell.addElement(new Phrase(" ", NORMAL_FONT));
        rightCell.addElement(new Phrase("Guest Signature", NORMAL_FONT));
        rightCell.addElement(new Phrase("(Guest)", NORMAL_FONT));
        table.addCell(rightCell);
        
        section.add(table);
        section.add(Chunk.NEWLINE);
        section.add(Chunk.NEWLINE);
        
        // Footer
        Paragraph footer = new Paragraph();
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.add(new Chunk("Thank you for choosing Ocean View Resort!", NORMAL_FONT));
        footer.add(Chunk.NEWLINE);
        footer.add(new Chunk("This is a computer-generated bill and does not require signature.", NORMAL_FONT));
        
        section.add(footer);
        
        document.add(section);
    }

    /**
     * Add table row with label and value
     */
    private static void addTableRow(PdfPTable table, String label, String value, Font normalFont, Font boldFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, boldFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);
        table.addCell(labelCell);
        
        PdfPCell valueCell = new PdfPCell(new Phrase(value, normalFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }

    /**
     * Format currency amount
     */
    private static String formatCurrency(BigDecimal amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "LK"));
        return currencyFormat.format(amount);
    }
}

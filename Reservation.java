package com.oceanview.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model class representing a Reservation
 * Part of MVC Pattern - Model component
 */
public class Reservation {
    private int id;
    private String guestId;
    private String guestName;
    private String nicPassport;
    private String address;
    private String phone;
    private String email;
    private String roomType;
    private String acType;
    private int adults;
    private int children;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String mealPlan;
    private BigDecimal totalAmount;
    private String createdAt;
    private String updatedAt;

    public Reservation() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getNicPassport() {
        return nicPassport;
    }

    public void setNicPassport(String nicPassport) {
        this.nicPassport = nicPassport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Get total number of guests
     * @return Total guests (adults + children)
     */
    public int getTotalGuests() {
        return adults + children;
    }

    /**
     * Get number of nights
     * @return Number of nights between check-in and check-out
     */
    public long getNumberOfNights() {
        if (checkIn != null && checkOut != null) {
            return java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        }
        return 0;
    }

    /**
     * Get formatted check-in date
     * @return Formatted date string
     */
    public String getFormattedCheckIn() {
        if (checkIn != null) {
            return checkIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return "";
    }

    /**
     * Get formatted check-out date
     * @return Formatted date string
     */
    public String getFormattedCheckOut() {
        if (checkOut != null) {
            return checkOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return "";
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", guestId='" + guestId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", acType='" + acType + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

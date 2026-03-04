package com.oceanview.builder;

import com.oceanview.factory.RoomFactory;
import com.oceanview.model.Reservation;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Builder Pattern Implementation
 * Provides a flexible way to create Reservation objects
 * Ensures all required fields are set before building
 */
public class ReservationBuilder {
    private Reservation reservation;

    public ReservationBuilder() {
        this.reservation = new Reservation();
    }

    /**
     * Set guest ID (auto-generated)
     * @param guestId Guest ID
     * @return Builder instance
     */
    public ReservationBuilder setGuestId(String guestId) {
        reservation.setGuestId(guestId);
        return this;
    }

    /**
     * Set guest name
     * @param guestName Guest name
     * @return Builder instance
     */
    public ReservationBuilder setGuestName(String guestName) {
        reservation.setGuestName(guestName);
        return this;
    }

    /**
     * Set NIC/Passport
     * @param nicPassport NIC/Passport number
     * @return Builder instance
     */
    public ReservationBuilder setNicPassport(String nicPassport) {
        reservation.setNicPassport(nicPassport);
        return this;
    }

    /**
     * Set address
     * @param address Guest address
     * @return Builder instance
     */
    public ReservationBuilder setAddress(String address) {
        reservation.setAddress(address);
        return this;
    }

    /**
     * Set phone number
     * @param phone Phone number
     * @return Builder instance
     */
    public ReservationBuilder setPhone(String phone) {
        reservation.setPhone(phone);
        return this;
    }

    /**
     * Set email
     * @param email Email address
     * @return Builder instance
     */
    public ReservationBuilder setEmail(String email) {
        reservation.setEmail(email);
        return this;
    }

    /**
     * Set room type
     * @param roomType Room type
     * @return Builder instance
     */
    public ReservationBuilder setRoomType(String roomType) {
        reservation.setRoomType(roomType);
        return this;
    }

    /**
     * Set AC type
     * @param acType AC type
     * @return Builder instance
     */
    public ReservationBuilder setAcType(String acType) {
        reservation.setAcType(acType);
        return this;
    }

    /**
     * Set number of adults
     * @param adults Number of adults
     * @return Builder instance
     */
    public ReservationBuilder setAdults(int adults) {
        reservation.setAdults(adults);
        return this;
    }

    /**
     * Set number of children
     * @param children Number of children
     * @return Builder instance
     */
    public ReservationBuilder setChildren(int children) {
        reservation.setChildren(children);
        return this;
    }

    /**
     * Set check-in date
     * @param checkIn Check-in date
     * @return Builder instance
     */
    public ReservationBuilder setCheckIn(LocalDate checkIn) {
        reservation.setCheckIn(checkIn);
        return this;
    }

    /**
     * Set check-out date
     * @param checkOut Check-out date
     * @return Builder instance
     */
    public ReservationBuilder setCheckOut(LocalDate checkOut) {
        reservation.setCheckOut(checkOut);
        return this;
    }

    /**
     * Set meal plan
     * @param mealPlan Meal plan
     * @return Builder instance
     */
    public ReservationBuilder setMealPlan(String mealPlan) {
        reservation.setMealPlan(mealPlan);
        return this;
    }

    /**
     * Calculate and set total amount
     * @return Builder instance
     */
    public ReservationBuilder calculateTotalAmount() {
        if (reservation.getRoomType() == null || reservation.getAcType() == null ||
            reservation.getCheckIn() == null || reservation.getCheckOut() == null ||
            reservation.getMealPlan() == null) {
            throw new IllegalStateException("Required fields not set for calculation");
        }

        // Calculate room cost
        BigDecimal roomPrice = RoomFactory.getRoomPrice(reservation.getRoomType(), reservation.getAcType());
        long nights = java.time.temporal.ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
        BigDecimal roomCost = roomPrice.multiply(BigDecimal.valueOf(nights));

        // Calculate meal cost
        BigDecimal mealPrice = RoomFactory.getMealPrice(reservation.getMealPlan());
        int totalGuests = reservation.getAdults() + reservation.getChildren();
        BigDecimal mealCost = mealPrice.multiply(BigDecimal.valueOf(totalGuests)).multiply(BigDecimal.valueOf(nights));

        // Total cost
        BigDecimal totalCost = roomCost.add(mealCost);
        reservation.setTotalAmount(totalCost);

        return this;
    }

    /**
     * Build the reservation object
     * @return Built reservation object
     */
    public Reservation build() {
        // Validate required fields
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new IllegalStateException("Guest name is required");
        }
        if (reservation.getRoomType() == null) {
            throw new IllegalStateException("Room type is required");
        }
        if (reservation.getAcType() == null) {
            throw new IllegalStateException("AC type is required");
        }
        if (reservation.getCheckIn() == null || reservation.getCheckOut() == null) {
            throw new IllegalStateException("Check-in and check-out dates are required");
        }
        if (reservation.getMealPlan() == null) {
            throw new IllegalStateException("Meal plan is required");
        }

        // Calculate total amount if not already calculated
        if (reservation.getTotalAmount() == null) {
            calculateTotalAmount();
        }

        return reservation;
    }

    /**
     * Reset builder for new reservation
     * @return Reset builder instance
     */
    public ReservationBuilder reset() {
        this.reservation = new Reservation();
        return this;
    }
}

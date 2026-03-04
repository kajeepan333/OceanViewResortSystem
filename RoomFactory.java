package com.oceanview.factory;

import java.math.BigDecimal;

/**
 * Factory Pattern Implementation
 * Creates room pricing based on room type and AC type
 * This centralizes room pricing logic and makes it easy to modify
 */
public class RoomFactory {

    /**
     * Get room price based on room type and AC type
     * @param roomType Room type (Standard, Deluxe, Suite)
     * @param acType AC type (A/C, Non A/C)
     * @return Room price per night
     */
    public static BigDecimal getRoomPrice(String roomType, String acType) {
        if (roomType == null || acType == null) {
            throw new IllegalArgumentException("Room type and AC type cannot be null");
        }

        switch (roomType) {
            case "Standard":
                return "A/C".equals(acType) ? new BigDecimal("6000") : new BigDecimal("4000");
            case "Deluxe":
                return "A/C".equals(acType) ? new BigDecimal("10000") : new BigDecimal("8000");
            case "Suite":
                return "A/C".equals(acType) ? new BigDecimal("16000") : new BigDecimal("14000");
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

    /**
     * Get meal plan price per person per day
     * @param mealPlan Meal plan (Only Breakfast, Breakfast + Lunch, 3 Times)
     * @return Meal price per person per day
     */
    public static BigDecimal getMealPrice(String mealPlan) {
        if (mealPlan == null) {
            throw new IllegalArgumentException("Meal plan cannot be null");
        }

        switch (mealPlan) {
            case "Only Breakfast":
                return new BigDecimal("500");
            case "Breakfast + Lunch":
                return new BigDecimal("2000");
            case "3 Times":
                return new BigDecimal("3500");
            default:
                throw new IllegalArgumentException("Invalid meal plan: " + mealPlan);
        }
    }

    /**
     * Get all available room types
     * @return Array of room types
     */
    public static String[] getRoomTypes() {
        return new String[]{"Standard", "Deluxe", "Suite"};
    }

    /**
     * Get all available AC types
     * @return Array of AC types
     */
    public static String[] getAcTypes() {
        return new String[]{"A/C", "Non A/C"};
    }

    /**
     * Get all available meal plans
     * @return Array of meal plans
     */
    public static String[] getMealPlans() {
        return new String[]{"Only Breakfast", "Breakfast + Lunch", "3 Times"};
    }
}

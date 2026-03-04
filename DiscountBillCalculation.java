package com.oceanview.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Discount Bill Calculation Strategy
 * Implements discounted pricing for loyal customers or special occasions
 */
public class DiscountBillCalculation implements BillCalculationStrategy {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.18"); // 18%
    private static final BigDecimal SERVICE_CHARGE_RATE = new BigDecimal("0.05"); // 5%
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10"); // 10% discount

    @Override
    public BigDecimal calculateFinalAmount(BigDecimal subtotal) {
        // Apply 10% discount
        BigDecimal discount = subtotal.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountedSubtotal = subtotal.subtract(discount);
        
        // Calculate VAT (18%) on discounted amount
        BigDecimal vat = discountedSubtotal.multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);
        
        // Calculate Service Charge (5%) on discounted amount
        BigDecimal serviceCharge = discountedSubtotal.multiply(SERVICE_CHARGE_RATE).setScale(2, RoundingMode.HALF_UP);
        
        // Final amount = discounted subtotal + VAT + service charge
        BigDecimal finalAmount = discountedSubtotal.add(vat).add(serviceCharge);
        
        return finalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getStrategyDescription() {
        return "Discount Pricing: 10% Discount + 18% VAT + 5% Service Charge";
    }

    /**
     * Get discount amount
     * @param subtotal Subtotal amount
     * @return Discount amount
     */
    public BigDecimal getDiscountAmount(BigDecimal subtotal) {
        return subtotal.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Get VAT amount after discount
     * @param subtotal Original subtotal amount
     * @return VAT amount
     */
    public BigDecimal getVatAmount(BigDecimal subtotal) {
        BigDecimal discount = getDiscountAmount(subtotal);
        BigDecimal discountedSubtotal = subtotal.subtract(discount);
        return discountedSubtotal.multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Get service charge amount after discount
     * @param subtotal Original subtotal amount
     * @return Service charge amount
     */
    public BigDecimal getServiceChargeAmount(BigDecimal subtotal) {
        BigDecimal discount = getDiscountAmount(subtotal);
        BigDecimal discountedSubtotal = subtotal.subtract(discount);
        return discountedSubtotal.multiply(SERVICE_CHARGE_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}

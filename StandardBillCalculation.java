package com.oceanview.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Standard Bill Calculation Strategy
 * Implements regular pricing with VAT and service charge
 */
public class StandardBillCalculation implements BillCalculationStrategy {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.18"); // 18%
    private static final BigDecimal SERVICE_CHARGE_RATE = new BigDecimal("0.05"); // 5%

    @Override
    public BigDecimal calculateFinalAmount(BigDecimal subtotal) {
        // Calculate VAT (18%)
        BigDecimal vat = subtotal.multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);
        
        // Calculate Service Charge (5%)
        BigDecimal serviceCharge = subtotal.multiply(SERVICE_CHARGE_RATE).setScale(2, RoundingMode.HALF_UP);
        
        // Final amount = subtotal + VAT + service charge
        BigDecimal finalAmount = subtotal.add(vat).add(serviceCharge);
        
        return finalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getStrategyDescription() {
        return "Standard Pricing: 18% VAT + 5% Service Charge";
    }

    /**
     * Get VAT amount
     * @param subtotal Subtotal amount
     * @return VAT amount
     */
    public BigDecimal getVatAmount(BigDecimal subtotal) {
        return subtotal.multiply(VAT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Get service charge amount
     * @param subtotal Subtotal amount
     * @return Service charge amount
     */
    public BigDecimal getServiceChargeAmount(BigDecimal subtotal) {
        return subtotal.multiply(SERVICE_CHARGE_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}

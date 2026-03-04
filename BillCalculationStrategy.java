package com.oceanview.strategy;

import java.math.BigDecimal;

/**
 * Strategy Pattern Implementation
 * Interface for different bill calculation strategies
 */
public interface BillCalculationStrategy {
    
    /**
     * Calculate final bill amount with taxes and service charges
     * @param subtotal Subtotal amount
     * @return Final amount with taxes and service charges
     */
    BigDecimal calculateFinalAmount(BigDecimal subtotal);
    
    /**
     * Get strategy description
     * @return Description of the strategy
     */
    String getStrategyDescription();
}

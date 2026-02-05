package com.moneyplusplus.design_system.component.chart.calculation

/**
 * Calculates the upper and lower bounds for the Y-axis.
 * These bounds are used to scale data points within the chart area.
 */
internal object YAxisBoundsCalculator {
    
    /**
     * Calculates the upper bound for the Y-axis.
     * Adds a small buffer (1.0) above the maximum value for visual spacing.
     *
     * @param data The list of data values
     * @return The upper bound value
     */
    fun calculateUpperBound(data: List<Double>): Double {
        return data.maxOrNull()?.plus(1.0) ?: 0.0
    }
    
    /**
     * Calculates the lower bound for the Y-axis.
     *
     * @param data The list of data values
     * @return The lower bound value (minimum value in data, or 0 if empty)
     */
    fun calculateLowerBound(data: List<Double>): Double {
        return data.minOrNull() ?: 0.0
    }
    
    /**
     * Calculates both upper and lower bounds as a pair.
     *
     * @param data The list of data values
     * @return A Pair of (lowerBound, upperBound)
     */
    fun calculateBounds(data: List<Double>): Pair<Double, Double> {
        return calculateLowerBound(data) to calculateUpperBound(data)
    }
}

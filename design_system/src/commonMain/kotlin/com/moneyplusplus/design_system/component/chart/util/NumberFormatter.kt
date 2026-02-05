package com.moneyplusplus.design_system.component.chart.util

import kotlin.math.abs

/**
 * Utility functions for formatting numbers in the chart.
 */
internal object NumberFormatter {
    
    /**
     * Formats a number using compact notation (K, M, B).
     * Used for axis labels where space is limited.
     *
     * Examples:
     * - 500 -> "500"
     * - 1500 -> "1.5K"
     * - 1500000 -> "1.5M"
     * - 1500000000 -> "1.5B"
     *
     * @param value The value to format
     * @return The formatted string
     */
    fun formatCompactNumber(value: Float): String {
        val absValue = abs(value)
        return when {
            absValue < 1000 -> value.toInt().toString()
            absValue < 1_000_000 -> formatWithSuffix(value / 1_000, "K")
            absValue < 1_000_000_000 -> formatWithSuffix(value / 1_000_000, "M")
            absValue < 1_000_000_000_000 -> formatWithSuffix(value / 1_000_000_000, "B")
            else -> "Infinity"
        }
    }
    
    /**
     * Formats a value with a suffix (K, M, B).
     */
    private fun formatWithSuffix(value: Float, suffix: String): String {
        val intValue = value.toInt()
        val decimalPart = ((value - intValue) * 10).toInt()
        return "$intValue.$decimalPart$suffix"
    }
    
    /**
     * Formats a number with comma separators for thousands.
     * Used for tooltip values where we want full readability.
     *
     * Examples:
     * - 1000 -> "1,000"
     * - 1000000 -> "1,000,000"
     *
     * @param value The value to format
     * @return The formatted string with commas
     */
    fun formatWithCommas(value: Float): String {
        return value.toInt().toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }
}

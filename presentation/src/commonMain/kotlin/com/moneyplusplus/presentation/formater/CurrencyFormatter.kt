package com.moneyplusplus.presentation.formater

import kotlin.math.roundToInt
/**
 * A utility object for handling Currency formatting.
 */
object CurrencyFormatter {
    /**
     * Formats a Double to a currency-like string with commas and smart decimals.
     *
     * Examples:
     * - 1234.567 -> "1,234.57"
     * - 5000.0   -> "5,000"
     *
     * @param amount The value to format.
     * @return Formatted string (e.g., "1,234.56").
     */
    fun format(amount: Double): String {
        val roundedAmount = (amount * 100.0).roundToInt() / 100.0
        val isInteger = roundedAmount % 1.0 == 0.0
        val text = if (isInteger) {
            roundedAmount.toLong().toString()
        } else {
            roundedAmount.toString()
        }

        val parts = text.split(".")
        val integerPart = parts[0]
        val fractionalPart = if (parts.size > 1) ".${parts[1]}" else ""

        val regex = "(\\d)(?=(\\d{3})+$)".toRegex()
        val formattedInteger = integerPart.replace(regex, "$1,")

        return formattedInteger + fractionalPart
    }
}
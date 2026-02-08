package com.moneyplusplus.presentation.formater

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

/**
 * A utility object for handling date formatting.
 */
object DateFormatter {

    /**
     * Formats a [LocalDateTime] into a readable string with format "dd MMM yyyy".
     *
     * Example Output: "8 Feb 2026"
     *
     * @param date The local date time to format.
     * @return A formatted string representing the date.
     */
    fun formatTransactionDate(date: LocalDateTime): String {
        val monthName = date.month.name.take(3).lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
        return "${date.day} $monthName ${date.year}"
    }

    /**
     * Formats a [LocalDate] into a Month and Year string "MMMM, yyyy".
     *
     * Example Output: "February, 2026"
     *
     * @param date The local date to format.
     * @return A formatted string representing the month and year.
     */
    fun formatMonthYear(date: LocalDate): String {
        val monthName = date.month.name.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
        return "$monthName, ${date.year}"
    }
}
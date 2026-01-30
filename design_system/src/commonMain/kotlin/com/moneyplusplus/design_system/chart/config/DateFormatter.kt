package com.moneyplusplus.design_system.chart.config

import kotlinx.datetime.LocalDate


object DateFormatter {

    fun format(date: LocalDate, pattern: DatePattern): String {
        return when (pattern) {
            DatePattern.DAY_MONTH_SHORT -> "${date.dayOfMonth} ${getMonthShort(date.monthNumber)}"
            DatePattern.MONTH_SHORT -> getMonthShort(date.monthNumber)
            DatePattern.WEEKDAY_SHORT -> getWeekdayShort(date.dayOfWeek.ordinal)
            DatePattern.DAY_ONLY -> date.dayOfMonth.toString()
        }
    }

    fun formatForTooltip(date: LocalDate): String {
        return "${date.dayOfMonth} ${getMonthFull(date.monthNumber)}"
    }

    private fun getMonthShort(month: Int): String = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )[month - 1]

    private fun getMonthFull(month: Int): String = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )[month - 1]

    private fun getWeekdayShort(day: Int): String = listOf(
        "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
    )[day]
}
package com.moneyplusplus.presentation.formater

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun LocalDateTime.formatDate(): String {
    val monthName = this.month.name.take(3).lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
    return "$day $monthName ${this.year}"
}



fun LocalDate.formatToMonthYearString(): String {
    val monthName = this.month.name.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
    val year = this.year
    return "$monthName, $year"
}
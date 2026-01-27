package com.moneyplusplus.presentation.util

import kotlinx.datetime.LocalDateTime

fun LocalDateTime.formatDate(): String {
    val monthName = this.month.name.take(3).lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
    return "$day $monthName ${this.year}"
}

fun Double.formatAmount(): String {
    val number = if (this % 1.0 == 0.0) {
        this.toLong().toString()
    } else {
        this.toString()
    }
    val regex = "(\\d)(?=(\\d{3})+$)".toRegex()
    return number.replace(regex, "$1,")
}
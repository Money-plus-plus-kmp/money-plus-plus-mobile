package com.moneyplusplus.presentation.formater

fun Double.formatAmount(): String {
    val isInteger = this % 1.0 == 0.0
    val text = if (isInteger) {
        this.toLong().toString()
    } else {
        this.toString()
    }

    val parts = text.split(".")
    val integerPart = parts[0]
    val fractionalPart = if (parts.size > 1) ".${parts[1]}" else ""

    val regex = "(\\d)(?=(\\d{3})+$)".toRegex()
    val formattedInteger = integerPart.replace(regex, "$1,")

    return formattedInteger + fractionalPart
}
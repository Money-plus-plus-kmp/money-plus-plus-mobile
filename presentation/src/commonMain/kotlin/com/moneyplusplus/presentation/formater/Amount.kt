package com.moneyplusplus.presentation.formater

import kotlin.math.roundToInt

fun Double.formatAmount(): String {
    val roundedAmount = (this * 100.0).roundToInt() / 100.0
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
package com.moneyplusplus.presentation.statistics.util

internal object AmountFormatter {

    fun formatWithCommas(value: Double): String {
        val longValue = value.toLong()
        return longValue
            .toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }

    fun formatWithCurrency(value: Double, currency: String): String {
        return "${formatWithCommas(value)} $currency"
    }
}

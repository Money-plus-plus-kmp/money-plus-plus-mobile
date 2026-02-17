package com.moneyplusplus.design_system.component.chart.util

import kotlin.math.abs

internal object NumberFormatter {

    fun formatWithCommas(value: Float): String {
        return value.toInt().toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }

    fun formatCompactNumber(value: Float): String {
        val absValue = abs(value)
        return when {
            absValue < 1000 -> formatWholeNumber(value)
            absValue < 1_000_000 -> formatWithSuffix(value / 1_000, "K")
            absValue < 1_000_000_000 -> formatWithSuffix(value / 1_000_000, "M")
            absValue < 1_000_000_000_000 -> formatWithSuffix(value / 1_000_000_000, "B")
            else -> "∞"
        }
    }

    private fun formatWholeNumber(value: Float): String {
        val intValue = value.toInt()
        return if (value == intValue.toFloat()) {
            intValue.toString()
        } else {
            val rounded = kotlin.math.round(value * 10) / 10
            val text = rounded.toString()
            text.dropLast(2)
        }
    }

    private fun formatWithSuffix(value: Float, suffix: String): String {
        val intValue = value.toInt()
        val decimalPart = ((value - intValue) * 10).toInt()
        return if (decimalPart == 0) {
            "$intValue$suffix"
        } else {
            "$intValue.$decimalPart$suffix"
        }
    }
}

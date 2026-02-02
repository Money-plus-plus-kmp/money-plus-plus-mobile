package com.moneyplusplus.design_system.chart.utils

import kotlin.math.abs

fun Float.format(status:String): String {
    val intValue = this.toInt()
    val floatValue = this - intValue
    val decimalPart = (floatValue * 10).toInt()
    return "$intValue.$decimalPart$status"
}

// Keep K/M/B for Axis
internal fun Float.formatToThousandsMillionsBillions(): String {
    val absValue = abs(this)
    return when {
        absValue < 1000 -> this.toInt().toString()
        absValue < 1_000_000 -> (this / 1_000).format("K")
        absValue < 1_000_000_000 -> (this / 1_000_000).format("M")
        absValue < 1_000_000_000_000 -> (this / 1_000_000_000).format("B")
        else -> "Infinity"
    }
}

// New for Tooltip
internal fun Float.formatWithCommas(): String {
    val intValue = this.toInt()
    val str = intValue.toString()
    val reversed = str.reversed()
    val chunked = reversed.chunked(3)
    val joined = chunked.joinToString(",")
    return joined.reversed()
}

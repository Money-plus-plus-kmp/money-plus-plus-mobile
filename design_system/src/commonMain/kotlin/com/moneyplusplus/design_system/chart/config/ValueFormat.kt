package com.moneyplusplus.design_system.chart.config


data class ValueFormat(
    val prefix: String = "",
    val suffix: String = "",
    val abbreviate: Boolean = true
) {
    companion object {
        val IQD = ValueFormat(suffix = "IQD")
        val NUMBER = ValueFormat()

    }
}
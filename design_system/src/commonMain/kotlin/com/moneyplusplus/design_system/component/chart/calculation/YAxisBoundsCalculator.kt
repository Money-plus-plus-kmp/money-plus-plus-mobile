package com.moneyplusplus.design_system.component.chart.calculation



internal object YAxisBoundsCalculator {
    fun calculateUpperBound(data: List<Double>): Double {
        return data.maxOrNull()?.plus(1.0) ?: 0.0
    }

    fun calculateLowerBound(data: List<Double>): Double {
        return data.minOrNull() ?: 0.0
    }
}
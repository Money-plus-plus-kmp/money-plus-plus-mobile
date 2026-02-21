package com.moneyplusplus.domain.entity

data class MonthlyOverview(
    val income: Double,
    val expenses: Double,
    val currency: String,
    val maxValue: Double,
    val scaleLabels: List<String>,
) {
    val savings: Double
        get() = income - expenses

    val hasSavings: Boolean
        get() = savings > 0

    val isEmpty: Boolean
        get() = income == 0.0 && expenses == 0.0

    val incomePercentage: Float
        get() = if (maxValue > 0) (income / maxValue).coerceIn(0.0, 1.0).toFloat() else 0f

    val expensePercentage: Float
        get() = if (maxValue > 0) (expenses / maxValue).coerceIn(0.0, 1.0).toFloat() else 0f
}

package com.moneyplusplus.design_system.chart.defaults

import com.moneyplusplus.design_system.chart.data.ChartData
import com.moneyplusplus.design_system.chart.data.ChartPoint
import kotlinx.datetime.LocalDate


object SampleData {

    fun spendingTrend(): ChartData = ChartData(
        points = listOf(
            ChartPoint(LocalDate(2024, 12, 1), 50000.0),
            ChartPoint(LocalDate(2024, 12, 2), 75000.0),
            ChartPoint(LocalDate(2024, 12, 3), 45000.0),
            ChartPoint(LocalDate(2024, 12, 4), 90000.0),
            ChartPoint(LocalDate(2024, 12, 5), 60000.0),
            ChartPoint(LocalDate(2024, 12, 6), 150000.0),
            ChartPoint(LocalDate(2024, 12, 7), 120000.0),
            ChartPoint(LocalDate(2024, 12, 8), 180000.0),
        ),
        title = "Spending Trend"
    )


    fun weeklySpending(): ChartData = ChartData(
        points = listOf(
            ChartPoint(LocalDate(2024, 12, 2), 15000.0),  // Mon
            ChartPoint(LocalDate(2024, 12, 3), 22000.0),  // Tue
            ChartPoint(LocalDate(2024, 12, 4), 18000.0),  // Wed
            ChartPoint(LocalDate(2024, 12, 5), 25000.0),  // Thu
            ChartPoint(LocalDate(2024, 12, 6), 30000.0),  // Fri
            ChartPoint(LocalDate(2024, 12, 7), 45000.0),  // Sat
            ChartPoint(LocalDate(2024, 12, 8), 35000.0),  // Sun
        ),
        title = "Weekly Spending"
    )


    fun monthlyIncome(): ChartData = ChartData(
        points = listOf(
            ChartPoint(LocalDate(2024, 1, 15), 1200000.0),
            ChartPoint(LocalDate(2024, 2, 15), 1350000.0),
            ChartPoint(LocalDate(2024, 3, 15), 1100000.0),
            ChartPoint(LocalDate(2024, 4, 15), 1500000.0),
            ChartPoint(LocalDate(2024, 5, 15), 1400000.0),
            ChartPoint(LocalDate(2024, 6, 15), 1650000.0),
        ),
        title = "Monthly Income"
    )
}
package com.moneyplusplus.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.SpendingTrendChart
import com.moneyplusplus.design_system.component.chart.data.ChartPoint
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import kotlinx.datetime.LocalDate

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) {

        Spacer(
            modifier = Modifier.height(50.dp)
        )

        SpendingTrendChart(
            title = "Spending Trend", points = listOf(
                ChartPoint(
                    LocalDate(2024, 12, 1), 50000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 2), 75000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 3), 60000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 4), 85000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 5), 90000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 6), 150000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 7), 200000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 8), 180000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 9), 120000.0
                ),
                ChartPoint(
                    LocalDate(2024, 12, 10), 100000.0
                ),
            ), valueSuffix = "EG"
        )
    }
}

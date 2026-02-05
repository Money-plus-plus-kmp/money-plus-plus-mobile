package com.moneyplusplus.presentation.statistics

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.data.ChartPoint
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.presentation.statistics.component.SpendingTrendChart
import kotlinx.datetime.LocalDate


@Composable
fun StatisticsScreen() {
    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) {

        Spacer(
            modifier = Modifier.height(50.dp)
        )

        SpendingTrendChart(
            title = "Spending Trend", points = listOf(
                ChartPoint(LocalDate(2024, 12, 1), 500.0),
                ChartPoint(LocalDate(2024, 12, 2), 705.0),
                ChartPoint(LocalDate(2024, 12, 3), 60.0),
                ChartPoint(LocalDate(2024, 12, 4), 815.0),
                ChartPoint(LocalDate(2024, 12, 5), 90.0),
                ChartPoint(LocalDate(2024, 12, 6), 1501.0),
                ChartPoint(LocalDate(2024, 12, 7), 2010.0),
                ChartPoint(LocalDate(2024, 12, 8), 1100.0),
                ChartPoint(LocalDate(2024, 12, 9), 120.0),
                ChartPoint(LocalDate(2024, 12, 10), 100.0),
                ChartPoint(LocalDate(2024, 12, 11), 550.0),
                ChartPoint(LocalDate(2024, 12, 12), 765.0),
                ChartPoint(LocalDate(2024, 12, 13), 608.0),
                ChartPoint(LocalDate(2024, 12, 14), 855.0),
                ChartPoint(LocalDate(2024, 12, 15), 960.0),
                ChartPoint(LocalDate(2024, 12, 16), 1520.0),
                ChartPoint(LocalDate(2024, 12, 17), 2040.0),
                ChartPoint(LocalDate(2024, 12, 18), 1860.0),
                ChartPoint(LocalDate(2024, 12, 19), 1290.0),
                ChartPoint(LocalDate(2024, 12, 20), 1070.0),
            ), valueSuffix = "EG"
        )
    }
}

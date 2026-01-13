package com.moneyplusplus.money

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.config.ChartColorScheme
import com.moneyplusplus.design_system.chart.config.SpendingChartConfig
import com.moneyplusplus.design_system.chart.config.ValueFormat
import com.moneyplusplus.design_system.chart.defaults.SampleData
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.moneyplusplus.design_system.chart.components.SpendingTrendChart


@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SpendingTrendChart(
                data = SampleData.monthlyIncome(),
                config = SpendingChartConfig(
                    colorScheme = ChartColorScheme.RED,
                    valueFormat = ValueFormat.IQD,
                    showGrid = true,
                    showXAxis = true,
                    showYAxis = true
                )
            )
        }
    }
}
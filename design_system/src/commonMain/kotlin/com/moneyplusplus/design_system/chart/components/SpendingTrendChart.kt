package com.moneyplusplus.design_system.chart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.baseComponents.model.GridOrientation
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.lineChart.LineChart
import com.moneyplusplus.design_system.chart.config.SpendingChartConfig
import com.moneyplusplus.design_system.chart.data.ChartData
import com.moneyplusplus.design_system.chart.mapper.ChartDataMapper

@Composable
fun SpendingTrendChart(
    data: ChartData,
    modifier: Modifier = Modifier,
    config: SpendingChartConfig = SpendingChartConfig.DEFAULT
) {
    if (!data.isValid) {
        EmptyChartPlaceholder(modifier)
        return
    }

    val xAxisData = ChartDataMapper.toXAxisData(data, config.datePattern)
    val lineParameters = ChartDataMapper.toLineParameters(data, config)
    val yAxisStyle = ChartDataMapper.toYAxisStyle(config)
    val xAxisStyle = ChartDataMapper.toXAxisStyle(config)

    Column(modifier = modifier) {
        // Title
        data.title?.let { title ->
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Chart
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            linesParameters = lineParameters,
            xAxisData = xAxisData,
            gridColor = config.colorScheme.gridColor,
            isGrid = config.showGrid,
            animateChart = config.animate,
            showGridWithSpacer = true,
            yAxisStyle = yAxisStyle,
            xAxisStyle = xAxisStyle,
            yAxisRange = config.yAxisSteps,
            showXAxis = config.showXAxis,
            showYAxis = config.showYAxis,
            oneLineChart = false,
            gridOrientation = GridOrientation.HORIZONTAL,
            legendPosition = LegendPosition.DISAPPEAR,
        )
    }
}

@Composable
private fun EmptyChartPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = "No data available",
            color = Color.Gray
        )
    }
}
package com.moneyplusplus.presentation.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.LineChart
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.data.ChartPoint
import com.moneyplusplus.presentation.statistics.util.DateFormatter.formatAsXAxisLabels
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun SpendingTrendChart(
    points: List<ChartPoint>,
    title: String,
    modifier: Modifier = Modifier,
    valueSuffix: String?
) {
    if (points.isEmpty()) return

    val xAxisLabels = points.formatAsXAxisLabels()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SPENDING_CHART_HEIGHT)
            .clip(RoundedCornerShape(CONTAINER_CORNER_RADIUS))
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(CONTAINER_PADDING)
    ) {
        val config = ChartConfig.defaults()

        LineChart(
            modifier = Modifier.fillMaxSize(),
            data = points.map { it.value },
            title = title,
            config = config,
            valueSuffix = valueSuffix,
            xAxisLabels = xAxisLabels
        )
    }
}

private val SPENDING_CHART_HEIGHT = 238.dp
private val CONTAINER_CORNER_RADIUS = 12.dp
private val CONTAINER_PADDING = 16.dp
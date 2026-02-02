package com.moneyplusplus.design_system.chart.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.components.LineChart
import com.moneyplusplus.design_system.chart.models.LineParameters
import com.moneyplusplus.design_system.chart.models.TooltipConfig
import com.moneyplusplus.design_system.chart.config.ChartConfig
import com.moneyplusplus.design_system.chart.data.ChartData
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun SpendingTrendChart(
    data: ChartData,
    modifier: Modifier = Modifier,
    config: ChartConfig = ChartConfig.Default
) {
    if (data.isEmpty) return

    val xAxisData = data.points.map {
        val monthName = it.date.month.name.take(3).lowercase()
        "${it.date.day} ${monthName.replaceFirstChar { char -> char.titlecase() }}"
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(238.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(16.dp)
    ) {
        val lineColor = Theme.colorScheme.primary.primary
        val gridColor = Theme.colorScheme.stroke
        val axisLabelColor = Theme.colorScheme.hint
        val tooltipBackground = Theme.colorScheme.surface.surfaceHigh
        val tooltipTextColor = Theme.colorScheme.title

        val lineParameters = listOf(
            LineParameters(
                label = data.title,
                data = data.points.map { it.value },
                lineColor = lineColor,
                lineShadow = true,
                tooltipConfig = TooltipConfig(
                    enabled = true,
                    backgroundColor = tooltipBackground,
                    textColor = tooltipTextColor
                )
            )
        )

        LineChart(
            modifier = Modifier.fillMaxSize(),
            linesParameters = lineParameters,
            gridColor = gridColor,
            xAxisData = xAxisData,
            animateChart = config.animationEnabled,
            yAxisStyle = TextStyle(
                color = axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            xAxisStyle = TextStyle(
                color = axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            chartRatio = 0f,
            descriptionStyle = Theme.typography.label.medium.copy(color = axisLabelColor)
        )
    }
}
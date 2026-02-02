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
import com.moneyplusplus.design_system.chart.models.LineParameters
import com.moneyplusplus.design_system.chart.models.TooltipConfig
import com.moneyplusplus.design_system.chart.config.ChartConfig
import com.moneyplusplus.design_system.chart.data.ChartPoint
import com.moneyplusplus.design_system.chart.models.ChartColors
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun SpendingTrendChart(
    points: List<ChartPoint>,
    title: String,
    modifier: Modifier = Modifier,
    config: ChartConfig = ChartConfig.Default,
    valueSuffix: String = ""
) {
    if (points.isEmpty()) return

    val xAxisData = points.map {
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
        val chartColors = ChartColors(
            lineColor = Theme.colorScheme.primary.primary,
            gridColor = Theme.colorScheme.stroke,
            axisLabelColor = Theme.colorScheme.hint,
            tooltipBackground = Theme.colorScheme.surface.surfaceHigh,
            tooltipTextColor = Theme.colorScheme.title
        )

        val lineParameters = listOf(
            LineParameters(
                label = title,
                data = points.map { it.value },
                lineColor = chartColors.lineColor,
                lineShadow = true,
                tooltipConfig = TooltipConfig(
                    enabled = true,
                    backgroundColor = chartColors.tooltipBackground,
                    textColor = chartColors.tooltipTextColor,
                    valueSuffix = valueSuffix
                )
            )
        )

        LineChart(
            modifier = Modifier.fillMaxSize(),
            linesParameters = lineParameters,
            gridColor = chartColors.gridColor,
            xAxisData = xAxisData,
            animateChart = config.animationEnabled,
            yAxisStyle = TextStyle(
                color = chartColors.axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            xAxisStyle = TextStyle(
                color = chartColors.axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            chartRatio = 0f,
            descriptionStyle = Theme.typography.label.medium.copy(color = chartColors.axisLabelColor),
            yAxisRange = 4 // Reduced range for more spacing
        )
    }
}
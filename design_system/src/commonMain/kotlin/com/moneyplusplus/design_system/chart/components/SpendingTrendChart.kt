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
import com.moneyplusplus.design_system.chart.aay.LineChart
import com.moneyplusplus.design_system.chart.aay.model.LineParameters
import com.moneyplusplus.design_system.chart.aay.model.LineType
import com.moneyplusplus.design_system.chart.aay.model.MarkerStyle
import com.moneyplusplus.design_system.chart.aay.model.TooltipCornerRadii
import com.moneyplusplus.design_system.chart.aay.model.TooltipPosition
import com.moneyplusplus.design_system.chart.config.ChartConfig
import com.moneyplusplus.design_system.chart.data.ChartData
import com.moneyplusplus.design_system.chart.aay.model.TooltipConfig as AayTooltipConfig
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
            .background(Theme.colorScheme.surface.surfaceLow) // Theme integration
            .padding(16.dp)
    ) {
        val lineParameters = listOf(
            LineParameters(
                label = data.title,
                data = data.points.map { it.value },
                lineColor = config.colors.lineColor,
                lineType = LineType.CURVED_LINE, // Enforced
                lineShadow = true,
                tooltipConfig = AayTooltipConfig(
                    enabled = true,
                    backgroundColor = config.colors.tooltipBackground,
                    textColor = config.colors.axisLabelColor,
                    markerStyle = MarkerStyle.Solid,
                    position = TooltipPosition.Left,
                    cornerRadii = TooltipCornerRadii(
                        topLeft = 8.dp,
                        topRight = 8.dp,
                        bottomLeft = 8.dp,
                        bottomRight = 2.dp
                    )
                )
            )
        )

        LineChart(
            modifier = Modifier.fillMaxSize(),
            linesParameters = lineParameters,
            gridColor = config.colors.gridColor,
            xAxisData = xAxisData,
            animateChart = config.animationEnabled,
            yAxisStyle = TextStyle(
                color = config.colors.axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            xAxisStyle = TextStyle(
                color = config.colors.axisLabelColor,
                fontSize = config.dimensions.axisLabelSize
            ),
            chartRatio = 0f,
            descriptionStyle = Theme.typography.label.medium.copy(color = config.colors.axisLabelColor) // Theme integration
        )
    }
}
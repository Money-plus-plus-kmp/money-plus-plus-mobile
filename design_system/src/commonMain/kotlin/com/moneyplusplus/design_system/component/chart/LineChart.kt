package com.moneyplusplus.design_system.component.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.core.ChartContainer
import com.moneyplusplus.design_system.component.text.Text

/**
 * A line chart component for displaying data trends.
 *
 * Features:
 * - Smooth curved line between data points
 * - Animated line drawing
 * - Interactive tooltips on tap
 * - Horizontal scrolling for large datasets
 * - Customizable colors and styles
 *
 * @param modifier Modifier for the chart container
 * @param data List of Y-axis values to plot
 * @param title Title displayed above the chart
 * @param valueSuffix Suffix to append to values in tooltips (e.g., "$", "units")
 * @param xAxisLabels Labels for the X-axis (must match data size)
 * @param config Configuration for colors, styles, and behavior
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Double>,
    title: String,
    valueSuffix: String?,
    xAxisLabels: List<String> = emptyList(),
    config: ChartConfig = ChartConfig.defaults()
) {
    Box(modifier.wrapContentHeight()) {

        Column {

            if (title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = ChartDimensions.TITLE_BOTTOM_PADDING),
                    text = title,
                    style = config.styles.title,
                    color = config.colors.axisLabelColor
                )
            }

            ChartContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ChartDimensions.CHART_HEIGHT),
                data = data,
                config = config,
                valueSuffix = valueSuffix,
                xAxisLabels = xAxisLabels
            )

        }
    }
}

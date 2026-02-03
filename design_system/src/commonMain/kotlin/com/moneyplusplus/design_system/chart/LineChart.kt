package com.moneyplusplus.design_system.chart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.components.ChartContent
import com.moneyplusplus.design_system.chart.models.ChartColors
import com.moneyplusplus.design_system.chart.utils.ChartDefaultValues
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Double>,
    title: String,
    chartColors: ChartColors,
    valueSuffix: String = "",
    xAxisData: List<String> = emptyList(),
    barWidthPx: Dp = ChartDefaultValues.backgroundLineWidth,
    animateChart: Boolean = ChartDefaultValues.ANIMATED_CHART,
    showGridWithSpacer: Boolean = ChartDefaultValues.SHOW_BACKGROUND_WITH_SPACER,
    descriptionStyle: TextStyle = Theme.typography.label.medium,
    yAxisStyle: TextStyle = ChartDefaultValues.axesStyle,
    xAxisStyle: TextStyle = ChartDefaultValues.axesStyle,
    chartRatio: Float = ChartDefaultValues.chartRatio,
    yAxisRange: Int = ChartDefaultValues.yAxisRange,
) {
    val clickedPoints = remember { mutableStateListOf<Pair<Float, Float>>() }

    Box(modifier.wrapContentHeight()) {
        Column() {
            // Title
            if (title.isNotEmpty()) {
                Box(Modifier.padding(bottom = 16.dp)) {
                    BasicText(
                        text = title,
                        style = descriptionStyle.copy(color = chartColors.axisLabelColor)
                    )
                }
            }

            ChartContent(
                modifier = if (chartRatio == 0f) Modifier.wrapContentSize()
                else Modifier.aspectRatio(chartRatio).fillMaxSize(),
                data = data,
                // title parameter removed
                chartColors = chartColors,
                valueSuffix = valueSuffix,
                tooltipBackgroundColor = chartColors.tooltipBackground, 
                tooltipTextColor = chartColors.tooltipTextColor, 
                xAxisData = xAxisData,
                barWidthPx = barWidthPx,
                animateChart = animateChart,
                showGridWithSpacer = showGridWithSpacer,
                yAxisStyle = yAxisStyle,
                xAxisStyle = xAxisStyle,
                yAxisRange = yAxisRange,
                onChartClick = { x, y ->
                    clickedPoints.add(x to y)
                },
                clickedPoints = clickedPoints,
            )
        }
    }
}

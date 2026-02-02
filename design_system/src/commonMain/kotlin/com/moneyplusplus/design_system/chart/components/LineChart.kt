package com.moneyplusplus.design_system.chart.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.chart.models.LineParameters
import com.moneyplusplus.design_system.chart.utils.ChartDefaultValues


@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    linesParameters: List<LineParameters> = ChartDefaultValues.lineParameters,
    gridColor: Color = ChartDefaultValues.gridColor,
    xAxisData: List<String> = emptyList(),
    barWidthPx: Dp = ChartDefaultValues.backgroundLineWidth,
    animateChart: Boolean = ChartDefaultValues.ANIMATED_CHART,
    showGridWithSpacer: Boolean = ChartDefaultValues.SHOW_BACKGROUND_WITH_SPACER,
    descriptionStyle: TextStyle = ChartDefaultValues.descriptionDefaultStyle,
    yAxisStyle: TextStyle = ChartDefaultValues.axesStyle,
    xAxisStyle: TextStyle = ChartDefaultValues.axesStyle,
    chartRatio: Float = ChartDefaultValues.chartRatio,
    horizontalArrangement: Arrangement.Horizontal = ChartDefaultValues.headerArrangement,
    yAxisRange: Int = ChartDefaultValues.yAxisRange,
) {
    val clickedPoints = remember { mutableStateListOf<Pair<Float, Float>>() }

    Box(modifier.wrapContentHeight()) {
        Column() {
            LazyRow(
                horizontalArrangement = horizontalArrangement,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                items(linesParameters) { details ->
                    ChartDescription(
                        chartName = details.label,
                        descriptionStyle = descriptionStyle,
                    )
                }
            }

            ChartContent(
                modifier = if (chartRatio == 0f) Modifier.padding(top = 16.dp).wrapContentSize()
                else Modifier.padding(top = 16.dp).aspectRatio(chartRatio)
                    .fillMaxSize(),
                linesParameters = linesParameters,
                gridColor = gridColor,
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

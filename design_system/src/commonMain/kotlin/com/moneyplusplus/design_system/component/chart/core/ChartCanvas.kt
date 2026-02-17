package com.moneyplusplus.design_system.component.chart.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.core.line.LineShadowRenderer.drawLineShadow
import com.moneyplusplus.design_system.component.chart.core.line.LineRenderer.drawAnimatedLine
import com.moneyplusplus.design_system.component.chart.interaction.findClickedPointIndex
import com.moneyplusplus.design_system.component.chart.state.ChartState
import com.moneyplusplus.design_system.component.chart.tooltip.drawTooltipWithMarker

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun ChartCanvas(
    chartState: ChartState,
    config: ChartConfig,
    xAxisLabels: List<String>,
    data: List<Double>,
    valueSuffix: String?,
    animationProgress: Float,
    selectedPointIndex: Int?,
    textMeasurer: TextMeasurer,
    onPointClicked: (Int?) -> Unit
) {
    val density = LocalDensity.current

    Canvas(
        modifier = Modifier
            .width(chartState.chartWidth)
            .fillMaxSize()
            .pointerInput(chartState.points) {
                detectTapGestures { tapOffset ->
                    onPointClicked(findClickedPointIndex(tapOffset, chartState.points))
                }
            }
    ) {
        // Draw base layers (grid, axes)
        composeChartLayers(
            xAxisLabels = xAxisLabels,
            textMeasurer = textMeasurer,
            upperValue = chartState.upperValue,
            lowerValue = chartState.lowerValue,
            config = config,
            xRegionWidth = chartState.xAxisSegmentWidth
        )

        // Draw shadow
        drawLineShadow(
            linePath = chartState.linePath,
            lineColor = config.colors.lineColor,
            chartHeight = size.height.toDp().toPx(),
            xAxisLabelsHeight = chartState.xAxisLabelsHeight,
            xRegionWidth = chartState.xAxisSegmentWidth,
            animationProgress = animationProgress
        )

        // Draw line
        drawAnimatedLine(
            path = chartState.linePath,
            lineColor = config.colors.lineColor,
            animationProgress = animationProgress
        )

        // Draw tooltip if point selected
        selectedPointIndex?.let { index ->
            if (index in data.indices) {
                val (pointX, pointY) = chartState.points[index]
                drawTooltipWithMarker(
                    textMeasurer = textMeasurer,
                    pointIndex = index,
                    pointValue = data[index],
                    pointX = with(density) { pointX.toDp() },
                    pointY = pointY,
                    xAxisLabels = xAxisLabels,
                    lineColor = config.colors.lineColor,
                    valueSuffix = valueSuffix,
                    tooltipConfig = config.tooltip
                )
            }
        }
    }
}

package com.moneyplusplus.design_system.component.chart.core

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlinx.coroutines.delay
import com.moneyplusplus.design_system.component.chart.calculation.ChartLayoutCalculator
import com.moneyplusplus.design_system.component.chart.calculation.calculateAllPoints
import com.moneyplusplus.design_system.component.chart.calculation.calculateLowerBound
import com.moneyplusplus.design_system.component.chart.calculation.calculateUpperBound
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.core.line.CurvedLinePathBuilder
import com.moneyplusplus.design_system.component.chart.core.line.LineShadowRenderer.drawLineShadow
import com.moneyplusplus.design_system.component.chart.core.line.LineRenderer.drawAnimatedLine
import com.moneyplusplus.design_system.component.chart.interaction.ChartInteractionState
import com.moneyplusplus.design_system.component.chart.interaction.findClickedPointIndex
import com.moneyplusplus.design_system.component.chart.tooltip.drawTooltipWithMarker
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter
import com.moneyplusplus.design_system.component.chart.util.validateChartData

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun ChartContainer(
    modifier: Modifier,
    data: List<Double>,
    config: ChartConfig,
    valueSuffix: String?,
    xAxisLabels: List<String>
) {
    validateChartData(data, xAxisLabels)

    val textMeasurer = rememberTextMeasurer()
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    val interactionState = remember { ChartInteractionState() }

    val animatedProgress = remember {
        Animatable(if (config.animationEnabled) 0f else 1f)
    }

    var upperValue by rememberSaveable {
        mutableStateOf(
            calculateUpperBound(
                data
            )
        )
    }
    var lowerValue by rememberSaveable {
        mutableStateOf(
            calculateLowerBound(
                data
            )
        )
    }

    BoxWithConstraints(modifier = modifier) {
        val screenWidth = maxWidth

        val yMaxTextWidth = textMeasurer.measure(
            text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue.toFloat()))
        ).size.width

        val startOffset = ChartLayoutCalculator.calculateChartStartOffset(yMaxTextWidth, density)
        val calculatedWidth = ChartLayoutCalculator.calculateChartWidth(data.size, startOffset)
        val finalWidth = max(screenWidth, calculatedWidth)
        val xRegionWidth = ChartLayoutCalculator.calculateXAxisSegmentWidth(
            finalWidth,
            startOffset,
            xAxisLabels.size
        )

        val chartHeight = this@BoxWithConstraints.maxHeight
        val xAxisLabelsHeight = with(density) { ChartDimensions.X_AXIS_LABELS_HEIGHT.toPx() }

        val points = remember(data, startOffset, xRegionWidth, lowerValue, upperValue) {
            calculateAllPoints(
                data = data,
                startOffset = startOffset,
                xRegionWidth = xRegionWidth,
                lowerValue = lowerValue.toFloat(),
                upperValue = upperValue.toFloat(),
                chartHeight = with(density) { chartHeight.toPx() },
                xAxisLabelsHeight = xAxisLabelsHeight,
                verticalOffset = with(density) { VERTICAL_OFFSET.toPx() },
                density = density
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scrollState)
        ) {
            Canvas(
                modifier = Modifier
                    .width(finalWidth)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { tapOffset ->
                            val clickedIndex = findClickedPointIndex(tapOffset, points)
                            if (clickedIndex != null) {
                                interactionState.togglePointSelection(clickedIndex)
                            } else {
                                interactionState.clearSelection()
                            }
                        }
                    }
            ) {
                composeChartLayers(
                    xAxisLabels = xAxisLabels,
                    textMeasurer = textMeasurer,
                    upperValue = upperValue.toFloat(),
                    lowerValue = lowerValue.toFloat(),
                    config = config,
                    xRegionWidth = xRegionWidth
                )

                val linePath = CurvedLinePathBuilder.buildPath(points)

                drawLineShadow(
                    linePath = linePath,
                    lineColor = config.colors.lineColor,
                    chartHeight = size.height.toDp().toPx(),
                    xAxisLabelsHeight = xAxisLabelsHeight,
                    xRegionWidth = xRegionWidth,
                    animationProgress = animatedProgress.value
                )

                drawAnimatedLine(
                    path = linePath,
                    lineColor = config.colors.lineColor,
                    animationProgress = animatedProgress.value
                )

                interactionState.selectedPointIndex?.let { selectedIndex ->
                    if (selectedIndex in data.indices) {
                        val (pointX, pointY) = points[selectedIndex]
                        drawTooltipWithMarker(
                            textMeasurer = textMeasurer,
                            pointIndex = selectedIndex,
                            pointValue = data[selectedIndex],
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
    }

    LaunchedEffect(data, config.animationEnabled) {
        if (config.animationEnabled) {
            upperValue = calculateUpperBound(data)
            lowerValue = calculateLowerBound(data)
            delay(ANIMATION_START_DELAY_MS)
            animatedProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = LINE_ANIMATION_DURATION_MS,
                    easing = LinearEasing
                )
            )
        }
    }
}

private const val LINE_ANIMATION_DURATION_MS = 2000
private const val ANIMATION_START_DELAY_MS = 1000L
private val VERTICAL_OFFSET = 11.dp

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.max
import kotlinx.coroutines.delay
import com.moneyplusplus.design_system.component.chart.calculation.ChartBoundsCalculator
import com.moneyplusplus.design_system.component.chart.calculation.ChartPointCalculator
import com.moneyplusplus.design_system.component.chart.calculation.YAxisBoundsCalculator
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.constants.AnimationConstants
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.core.ChartLayerComposer.composeChartLayers
import com.moneyplusplus.design_system.component.chart.core.line.CurvedLinePathBuilder
import com.moneyplusplus.design_system.component.chart.core.line.LineRenderer.drawAnimatedLine
import com.moneyplusplus.design_system.component.chart.core.line.LineShadowRenderer.drawLineShadow
import com.moneyplusplus.design_system.component.chart.interaction.ChartInteractionState
import com.moneyplusplus.design_system.component.chart.interaction.ClickDetector
import com.moneyplusplus.design_system.component.chart.tooltip.drawTooltipWithMarker
import com.moneyplusplus.design_system.component.chart.util.DataValidator
import com.moneyplusplus.design_system.component.chart.util.NumberFormatter

/**
 * The main chart content container.
 * Handles layout, animation, and orchestrates all chart rendering.
 * 
 * - Layout and sizing logic
 * - Animation state management
 * - Coordinates all renderers (layers, line, shadow, tooltip)
 * - Touch interaction handling
 */
@OptIn(ExperimentalTextApi::class)
@Composable
internal fun ChartContainer(
    modifier: Modifier,
    data: List<Double>,
    config: ChartConfig,
    valueSuffix: String?,
    xAxisLabels: List<String>
) {
    // Validate data
    DataValidator.validateChartData(data, xAxisLabels)
    
    // State
    val textMeasurer = rememberTextMeasurer()
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    val interactionState = remember { ChartInteractionState() }
    
    // Animation state
    val animatedProgress = remember {
        Animatable(if (config.animationEnabled) 0f else 1f)
    }
    
    // Y-axis bounds
    var upperValue by rememberSaveable { mutableStateOf(YAxisBoundsCalculator.calculateUpperBound(data)) }
    var lowerValue by rememberSaveable { mutableStateOf(YAxisBoundsCalculator.calculateLowerBound(data)) }
    
    BoxWithConstraints(modifier = modifier) {
        val screenWidth = maxWidth
        
        // Calculate chart dimensions
        val yMaxTextWidth = textMeasurer.measure(
            text = AnnotatedString(NumberFormatter.formatCompactNumber(upperValue.toFloat()))
        ).size.width
        
        val startOffset = ChartBoundsCalculator.calculateStartOffset(yMaxTextWidth, density)
        val calculatedWidth = ChartBoundsCalculator.calculateChartWidth(data.size, startOffset)
        val finalWidth = max(screenWidth, calculatedWidth)
        val xRegionWidth = ChartBoundsCalculator.calculateXRegionWidth(finalWidth, startOffset, xAxisLabels.size)
        
        // Calculate all point coordinates once
        val chartHeight = this@BoxWithConstraints.maxHeight
        val points = remember(data, startOffset, xRegionWidth, lowerValue, upperValue) {
            ChartPointCalculator.calculateAllPoints(
                data = data,
                startOffset = startOffset,
                xRegionWidth = xRegionWidth,
                lowerValue = lowerValue.toFloat(),
                upperValue = upperValue.toFloat(),
                chartHeight = with(density) { chartHeight.toPx() },
                spacingY = with(density) { ChartDimensions.SPACING_Y.toPx() },
                verticalOffset = with(density) { ChartDimensions.VERTICAL_OFFSET.toPx() },
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
                            handleTap(
                                tapOffset = tapOffset,
                                points = points,
                                interactionState = interactionState,
                                tolerance = ChartDimensions.TOUCH_TOLERANCE.toPx()
                            )
                        }
                    }
            ) {
                // Draw base layers (grid, axes)
                composeChartLayers(
                    xAxisLabels = xAxisLabels,
                    textMeasurer = textMeasurer,
                    upperValue = upperValue.toFloat(),
                    lowerValue = lowerValue.toFloat(),
                    config = config,
                    xRegionWidth = xRegionWidth
                )
                
                // Build and draw line
                val linePath = CurvedLinePathBuilder.buildPath(points)
                
                // Draw shadow
                drawLineShadow(
                    linePath = linePath,
                    lineColor = config.colors.lineColor,
                    chartHeight = size.height.toDp().toPx(),
                    spacingY = ChartDimensions.SPACING_Y.toPx(),
                    xRegionWidth = xRegionWidth,
                    animationProgress = animatedProgress.value
                )
                
                // Draw line
                drawAnimatedLine(
                    path = linePath,
                    lineColor = config.colors.lineColor,
                    animationProgress = animatedProgress.value
                )
                
                // Draw tooltip if a point is selected
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
    
    // Handle animation
    LaunchedEffect(data, config.animationEnabled) {
        if (config.animationEnabled) {
            upperValue = YAxisBoundsCalculator.calculateUpperBound(data)
            lowerValue = YAxisBoundsCalculator.calculateLowerBound(data)
            delay(AnimationConstants.ANIMATION_START_DELAY_MS)
            animatedProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = AnimationConstants.LINE_ANIMATION_DURATION_MS,
                    easing = LinearEasing
                )
            )
        }
    }
}

/**
 * Handles tap gestures on the chart.
 */
private fun handleTap(
    tapOffset: Offset,
    points: List<Pair<Float, Float>>,
    interactionState: ChartInteractionState,
    tolerance: Float
) {
    val clickedIndex = ClickDetector.findClickedPointIndex(
        tapPosition = tapOffset,
        points = points,
        tolerance = tolerance
    )
    
    if (clickedIndex != null) {
        interactionState.togglePointSelection(clickedIndex)
    } else {
        interactionState.clearSelection()
    }
}

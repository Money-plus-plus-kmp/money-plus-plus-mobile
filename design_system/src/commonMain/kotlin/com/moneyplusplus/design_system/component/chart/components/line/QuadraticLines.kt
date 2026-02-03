package com.moneyplusplus.design_system.component.chart.components.line

import com.moneyplusplus.design_system.component.chart.components.tooltip.drawTooltipWithMarker
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.moneyplusplus.design_system.component.chart.models.ChartConfig
import com.moneyplusplus.design_system.component.chart.utils.ChartConstants
import com.moneyplusplus.design_system.component.chart.utils.clickedOnThisPoint
import com.moneyplusplus.design_system.component.chart.utils.formatToThousandsMillionsBillions

private var lastClickedPoint: Pair<Float, Float>? = null

@OptIn(ExperimentalTextApi::class)
internal fun DrawScope.drawQuarticLineWithShadow(
    data: List<Double>,
    config: ChartConfig,
    lineShadow: Boolean = true,
    valueSuffix: String?,
    lowerValue: Float,
    upperValue: Float,
    animatedProgress: Animatable<Float, AnimationVector1D>,
    clickedPoints: MutableList<Pair<Float, Float>>,
    xRegionWidth: Dp,
    textMeasurer: TextMeasurer,
    xAxisData: List<String> = emptyList(),
) {
    val strokePathOfQuadraticLine = drawLineAsQuadratic(
        data = data,
        config = config,
        valueSuffix = valueSuffix,
        lowerValue = lowerValue,
        upperValue = upperValue,
        animatedProgress = animatedProgress,
        clickedPoints = clickedPoints,
        textMeasurer = textMeasurer,
        xRegionWidth = xRegionWidth,
        xAxisData = xAxisData
    )

    if (lineShadow) {
        val fillPath = strokePathOfQuadraticLine.apply {
            lineTo(
                size.width - xRegionWidth.toPx() + ChartConstants.shadowOffset.toPx(),
                size.height * ChartConstants.gradientFillBottomFactor
            )
            lineTo(ChartConstants.pointWidth.toPx() * 2, size.height * ChartConstants.gradientFillBottomFactor)
            close()
        }
        clipRect(right = size.width * animatedProgress.value) {
            drawPath(
                path = fillPath, brush = Brush.verticalGradient(
                    colors = listOf(
                        config.colors.lineColor.copy(alpha = .32f), Color.Transparent
                    ), endY = (size.height.toDp() - ChartConstants.spacingY).toPx()
                )
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawLineAsQuadratic(
    data: List<Double>,
    config: ChartConfig,
    valueSuffix: String?,
    lowerValue: Float,
    upperValue: Float,
    animatedProgress: Animatable<Float, AnimationVector1D>,
    clickedPoints: MutableList<Pair<Float, Float>>,
    textMeasurer: TextMeasurer,
    xRegionWidth: Dp,
    xAxisData: List<String> = emptyList(),
) = Path().apply {
    var medX: Float
    val height = size.height.toDp()

    drawPathLineWrapper(
        data = data,
        lineColor = config.colors.lineColor,
        strokePath = this,
        animatedProgress = animatedProgress,
    ) { index ->

        val yMaxTextWidth = textMeasurer.measure(
            text = AnnotatedString(upperValue.formatToThousandsMillionsBillions()),
        ).size.width
        val textSpace = yMaxTextWidth - (yMaxTextWidth / 4)

        val info = data[index]
        val nextInfo = data.getOrNull(index + 1) ?: data.last()
        val firstRatio = (info - lowerValue) / (upperValue - lowerValue)
        val secondRatio = (nextInfo - lowerValue) / (upperValue - lowerValue)

        val xFirstPoint = (textSpace.toDp() + ChartConstants.textSpacing) + index * xRegionWidth
        val xSecondPoint =
            (textSpace.toDp() + ChartConstants.textSpacing) + (index + checkLastIndex(
                data,
                index
            )) * xRegionWidth

        val yFirstPoint = (height.toPx()
                + ChartConstants.verticalOffset.toPx()
                - ChartConstants.spacingY.toPx()
                - (firstRatio * (size.height.toDp() - ChartConstants.spacingY).toPx())
                )
        val ySecondPoint = (height.toPx()
                + ChartConstants.verticalOffset.toPx()
                - ChartConstants.spacingY.toPx()
                - (secondRatio * (size.height.toDp() - ChartConstants.spacingY).toPx())
                )

        val tolerance = ChartConstants.tolerance.toPx()
        val savedClicks =
            clickedOnThisPoint(
                clickedPoints,
                xFirstPoint.toPx(),
                yFirstPoint,
                tolerance
            )
        if (savedClicks) {
            if (lastClickedPoint != null) {
                clickedPoints.clear()
                lastClickedPoint = null
            } else {
                lastClickedPoint = Pair(xFirstPoint.toPx(), yFirstPoint.toFloat())
                drawTooltipWithMarker(
                    x = xFirstPoint,
                    y = yFirstPoint,
                    textMeasurer = textMeasurer,
                    xIndex = index,
                    yValue = info,
                    lineColor = config.colors.lineColor,
                    valueSuffix = valueSuffix,
                    tooltipConfig = config.tooltip,
                    xAxisData = xAxisData
                )
            }

        }

        if (index == 0) {
            moveTo(xFirstPoint.toPx(), yFirstPoint.toFloat())
            medX = ((xFirstPoint + xSecondPoint) / 2f).toPx()
            cubicTo(
                medX,
                yFirstPoint.toFloat(),
                medX,
                ySecondPoint.toFloat(),
                xSecondPoint.toPx(),
                ySecondPoint.toFloat()
            )
        } else {
            medX = ((xFirstPoint + xSecondPoint) / 2f).toPx()
            cubicTo(
                medX,
                yFirstPoint.toFloat(),
                medX,
                ySecondPoint.toFloat(),
                xSecondPoint.toPx(),
                ySecondPoint.toFloat()
            )
        }
    }
}

private fun checkLastIndex(data: List<Double>, index: Int): Int {
    return if (data[index] == data[data.lastIndex])
        0
    else
        1
}

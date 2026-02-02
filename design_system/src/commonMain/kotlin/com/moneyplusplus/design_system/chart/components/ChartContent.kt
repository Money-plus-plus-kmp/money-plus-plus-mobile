package com.moneyplusplus.design_system.chart.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.moneyplusplus.design_system.chart.drawing.baseChartContainer
import com.moneyplusplus.design_system.chart.drawing.drawQuarticLineWithShadow
import com.moneyplusplus.design_system.chart.models.LineParameters
import com.moneyplusplus.design_system.chart.utils.checkIfDataValid
import com.moneyplusplus.design_system.chart.utils.formatToThousandsMillionsBillions

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun ChartContent(
    modifier: Modifier,
    linesParameters: List<LineParameters>,
    gridColor: Color,
    xAxisData: List<String>,
    barWidthPx: Dp,
    animateChart: Boolean,
    showGridWithSpacer: Boolean,
    yAxisStyle: TextStyle,
    xAxisStyle: TextStyle,
    yAxisRange: Int,
    onChartClick: (Float, Float) -> Unit,
    clickedPoints: MutableList<Pair<Float, Float>>,
) {

    val textMeasure = rememberTextMeasurer()

    val animatedProgress = remember {
        if (animateChart) Animatable(0f) else Animatable(1f)
    }
    var upperValue by rememberSaveable {
        mutableStateOf(linesParameters.getUpperValue())
    }
    var lowerValue by rememberSaveable {
        mutableStateOf(linesParameters.getLowerValue())
    }
    checkIfDataValid(xAxisData = xAxisData, linesParameters = linesParameters)

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    onChartClick(offset.x, offset.y)
                }
            }
    ) {
        val yMaxTextWidth = textMeasure.measure(
            text = AnnotatedString(upperValue.toFloat().formatToThousandsMillionsBillions()),
        ).size.width
        val textSpace = yMaxTextWidth - (yMaxTextWidth/4)
        val startOffset = textSpace.toDp() + 10.dp
        val spacingX = (size.width / 50.dp.toPx()).dp
        val spacingY = (size.height / 8.dp.toPx()).dp

        val xRegionWidth = if (xAxisData.size > 1) {
            (size.width.toDp() - startOffset) / (xAxisData.size - 1)
        } else {
            size.width.toDp()
        }

        baseChartContainer(
            xAxisData = xAxisData,
            textMeasure = textMeasure,
            upperValue = upperValue.toFloat(),
            lowerValue = lowerValue.toFloat(),
            backgroundLineWidth = barWidthPx.toPx(),
            gridColor = gridColor,
            showGridWithSpacer = showGridWithSpacer,
            spacingY = spacingY,
            yAxisStyle = yAxisStyle,
            xAxisStyle = xAxisStyle,
            yAxisRange = yAxisRange,
            xRegionWidth = xRegionWidth
        )

        linesParameters.forEach { line ->
            drawQuarticLineWithShadow(
                line = line,
                lowerValue = lowerValue.toFloat(),
                upperValue = upperValue.toFloat(),
                animatedProgress = animatedProgress,
                spacingX = spacingX,
                spacingY = spacingY,
                clickedPoints = clickedPoints,
                xRegionWidth = xRegionWidth,
                textMeasurer = textMeasure,
                xAxisData = xAxisData
            )
        }
    }

    LaunchedEffect(linesParameters, animateChart) {
        if (animateChart) {

            collectToSnapShotFlow(linesParameters) {
                upperValue = it.getUpperValue()
                lowerValue = it.getLowerValue()
            }

            delay(400)
            animatedProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
        }
    }
}

private fun List<LineParameters>.getUpperValue(): Double {
    return this.flatMap { item -> item.data }.maxOrNull()?.plus(1.0) ?: 0.0
}

private fun List<LineParameters>.getLowerValue(): Double {
    return this.flatMap { item -> item.data }.minOrNull() ?: 0.0
}

private fun CoroutineScope.collectToSnapShotFlow(
    linesParameters: List<LineParameters>,
    makeUpdateData: (List<LineParameters>) -> Unit,
) {
    this.launch {
        snapshotFlow {
            linesParameters
        }.collect {
            makeUpdateData(it)
        }
    }
}

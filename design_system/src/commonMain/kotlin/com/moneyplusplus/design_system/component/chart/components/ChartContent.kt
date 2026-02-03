package com.moneyplusplus.design_system.component.chart.components

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.moneyplusplus.design_system.component.chart.components.grid.baseChartContainer
import com.moneyplusplus.design_system.component.chart.components.line.drawQuarticLineWithShadow
import com.moneyplusplus.design_system.component.chart.models.ChartConfig
import com.moneyplusplus.design_system.component.chart.utils.checkIfDataValid
import com.moneyplusplus.design_system.component.chart.utils.formatToThousandsMillionsBillions
import com.moneyplusplus.design_system.component.chart.utils.ChartConstants


@OptIn(ExperimentalTextApi::class)
@Composable
internal fun ChartContent(
    modifier: Modifier,
    data: List<Double>,
    config: ChartConfig,
    valueSuffix: String?,
    xAxisData: List<String>,
    onChartClick: (Float, Float) -> Unit,
    clickedPoints: MutableList<Pair<Float, Float>>,
) {
    val textMeasure = rememberTextMeasurer()
    val scrollState = rememberScrollState()

    val animatedProgress = remember {
        if (config.animationEnabled) Animatable(0f) else Animatable(1f)
    }
    var upperValue by rememberSaveable {
        mutableStateOf(data.getUpperValue())
    }
    var lowerValue by rememberSaveable {
        mutableStateOf(data.getLowerValue())
    }
    checkIfDataValid(
        xAxisData = xAxisData,
        data = data
    )

    BoxWithConstraints(modifier = modifier) {
        val screenWidth = maxWidth
        val pointWidth = ChartConstants.pointWidth 
        val yMaxTextWidth = textMeasure.measure(
            text = AnnotatedString(upperValue.toFloat().formatToThousandsMillionsBillions()),
        ).size.width
        val density = LocalDensity.current
        val textSpace = yMaxTextWidth - (yMaxTextWidth/4)
        val startOffset = with(density) { textSpace.toDp() } + ChartConstants.textSpacing

        val calculatedWidth = (pointWidth * data.size) + startOffset + 20.dp
        val finalWidth = max(screenWidth, calculatedWidth)

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
                        detectTapGestures { offset ->
                            onChartClick(offset.x, offset.y)
                        }
                    }
            ) {

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
                    config = config,
                    xRegionWidth = xRegionWidth
                )

                drawQuarticLineWithShadow(
                    data = data,
                    config = config,
                    lineShadow = true,
                    valueSuffix = valueSuffix,
                    lowerValue = lowerValue.toFloat(),
                    upperValue = upperValue.toFloat(),
                    animatedProgress = animatedProgress,
                    clickedPoints = clickedPoints,
                    xRegionWidth = xRegionWidth,
                    textMeasurer = textMeasure,
                    xAxisData = xAxisData
                )
            }
        }
    }

    LaunchedEffect(data, config.animationEnabled) {
        if (config.animationEnabled) {
            collectToSnapShotFlow(data) {
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

private fun List<Double>.getUpperValue(): Double {
    return this.maxOrNull()?.plus(1.0) ?: 0.0
}

private fun List<Double>.getLowerValue(): Double {
    return this.minOrNull() ?: 0.0
}

private fun CoroutineScope.collectToSnapShotFlow(
    data: List<Double>,
    makeUpdateData: (List<Double>) -> Unit,
) {
    this.launch {
        snapshotFlow {
            data
        }.collect {
            makeUpdateData(it)
        }
    }
}

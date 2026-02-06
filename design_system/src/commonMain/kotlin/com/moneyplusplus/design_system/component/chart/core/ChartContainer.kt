package com.moneyplusplus.design_system.component.chart.core

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.rememberTextMeasurer
import com.moneyplusplus.design_system.component.chart.animation.rememberChartAnimationProgress
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.interaction.ChartInteractionState
import com.moneyplusplus.design_system.component.chart.state.rememberChartState
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
    val interactionState = remember { ChartInteractionState() }

    val animationProgress = rememberChartAnimationProgress(
        animationEnabled = config.animationEnabled,
        dataKey = data
    )

    BoxWithConstraints(modifier = modifier) {
        val chartState = rememberChartState(
            data = data,
            xAxisLabels = xAxisLabels,
            screenWidth = maxWidth,
            chartHeight = maxHeight,
            textMeasurer = textMeasurer
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(scrollState)
        ) {
            ChartCanvas(
                chartState = chartState,
                config = config,
                xAxisLabels = xAxisLabels,
                data = data,
                valueSuffix = valueSuffix,
                animationProgress = animationProgress.value,
                selectedPointIndex = interactionState.selectedPointIndex,
                textMeasurer = textMeasurer,
                onPointClicked = { clickedIndex ->
                    if (clickedIndex != null) {
                        interactionState.togglePointSelection(clickedIndex)
                    } else {
                        interactionState.clearSelection()
                    }
                }
            )
        }
    }
}

package com.moneyplusplus.design_system.component.chart.state

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp

internal data class ChartState(
    val points: List<Pair<Float, Float>>,
    val linePath: Path,
    val upperValue: Float,
    val lowerValue: Float,
    val chartWidth: Dp,
    val xAxisSegmentWidth: Dp,
    val startOffset: Dp,
    val xAxisLabelsHeight: Float
)

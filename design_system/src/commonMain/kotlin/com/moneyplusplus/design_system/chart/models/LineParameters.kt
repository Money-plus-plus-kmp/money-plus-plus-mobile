package com.moneyplusplus.design_system.chart.models

import androidx.compose.ui.graphics.Color

data class LineParameters(
    val label: String,
    val data: List<Double>,
    val lineColor: Color,
    val lineShadow: Boolean,
    val tooltipConfig: TooltipConfig = TooltipConfig()
    // Removed valueSuffix from here
)

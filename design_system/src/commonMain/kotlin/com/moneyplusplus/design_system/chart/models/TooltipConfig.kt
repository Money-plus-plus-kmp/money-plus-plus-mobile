package com.moneyplusplus.design_system.chart.models

import androidx.compose.ui.graphics.Color

data class TooltipConfig(
    val enabled: Boolean = true,
    val backgroundColor: Color = Color.Black,
    val textColor: Color = Color.White,
    val valueSuffix: String = ""
)

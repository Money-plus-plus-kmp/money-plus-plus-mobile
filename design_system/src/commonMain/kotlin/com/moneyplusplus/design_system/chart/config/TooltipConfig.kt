package com.moneyplusplus.design_system.chart.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TooltipConfig(
    val enabled: Boolean = true,
    val backgroundColor: Color = Color.White,
    val borderColor: Color? = null,
    val textColor: Color = Color.Black,
    val textSize: TextUnit = 12.sp,
    val cornerRadius: Dp = 16.dp,
    val cornerRadii: TooltipCornerRadii? = null,
    val size: TooltipSize = TooltipSize.Auto,
    val content: TooltipContent = TooltipContent.XYValue(),
    val padding: Dp = 8.dp,
    val markerStyle: MarkerStyle = MarkerStyle.Stroke(),
    val position: TooltipPosition = TooltipPosition.Center,
    val offsetY: Dp = 10.dp
) {
    companion object {
        val Default = TooltipConfig()
        val Disabled = TooltipConfig(enabled = false)
    }
}

data class TooltipCornerRadii(
    val topLeft: Dp = 8.dp,
    val topRight: Dp = 8.dp,
    val bottomLeft: Dp = 8.dp,
    val bottomRight: Dp = 8.dp
)

sealed class TooltipPosition {
    object Center : TooltipPosition()
    
    object Left : TooltipPosition()
    
    object Right : TooltipPosition()
}

sealed class TooltipSize {
    object Auto : TooltipSize()

    data class Fixed(val width: Dp, val height: Dp) : TooltipSize()
}


sealed class TooltipContent {
    data class YValue(
        val label: String = "",
        val formatter: (Double) -> String = { formatValue(it) }
    ) : TooltipContent()
    
    data class XValue(
        val label: String = ""
    ) : TooltipContent()
    
    data class XYValue(
        val xLabel: String = "",
        val yLabel: String = "",
        val yFormatter: (Double) -> String = { formatValue(it) }
    ) : TooltipContent()
    
    data class Custom(
        val formatter: (xLabel: String, yValue: Double) -> String
    ) : TooltipContent()
}

sealed class MarkerStyle {
    data class Stroke(val strokeWidth: Dp = 2.dp) : MarkerStyle()
    
    object Solid : MarkerStyle()

    object None : MarkerStyle()
}

private fun formatValue(value: Double): String {
    return when {
        value >= 1_000_000_000 -> "${(value / 1_000_000_000).roundToOneDecimal()}B"
        value >= 1_000_000 -> "${(value / 1_000_000).roundToOneDecimal()}M"
        value >= 1_000 -> "${(value / 1_000).toLong()}K"
        else -> value.toLong().toString()
    }
}

private fun Double.roundToOneDecimal(): String {
    val rounded = kotlin.math.round(this * 10) / 10
    return if (rounded == rounded.toLong().toDouble()) {
        rounded.toLong().toString()
    } else {
        rounded.toString()
    }
}
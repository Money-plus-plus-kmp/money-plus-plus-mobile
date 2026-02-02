package com.moneyplusplus.design_system.chart.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Configuration for customizing the tooltip appearance and content.
 * 
 * Adapted from AAY-chart library for comprehensive tooltip customization.
 */
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

/**
 * Individual corner radii for the tooltip box.
 */
data class TooltipCornerRadii(
    val topLeft: Dp = 8.dp,
    val topRight: Dp = 8.dp,
    val bottomLeft: Dp = 8.dp,
    val bottomRight: Dp = 8.dp
)

/**
 * Defines the horizontal position of the tooltip relative to the clicked point.
 */
sealed class TooltipPosition {
    /** Tooltip is centered horizontally above the point */
    object Center : TooltipPosition()
    
    /** Tooltip is aligned to the left of the point */
    object Left : TooltipPosition()
    
    /** Tooltip is aligned to the right of the point */
    object Right : TooltipPosition()
}

/**
 * Defines the size of the tooltip box.
 */
sealed class TooltipSize {
    /** Automatically calculates size based on text content */
    object Auto : TooltipSize()
    
    /** Fixed size for the tooltip box */
    data class Fixed(val width: Dp, val height: Dp) : TooltipSize()
}

/**
 * Defines the content to display in the tooltip.
 */
sealed class TooltipContent {
    /** Display only the Y value (data point value) */
    data class YValue(
        val label: String = "",
        val formatter: (Double) -> String = { formatValue(it) }
    ) : TooltipContent()
    
    /** Display only the X value (axis label like date) */
    data class XValue(
        val label: String = ""
    ) : TooltipContent()
    
    /** Display both X and Y values (date and value) */
    data class XYValue(
        val xLabel: String = "",
        val yLabel: String = "",
        val yFormatter: (Double) -> String = { formatValue(it) }
    ) : TooltipContent()
    
    /** Custom tooltip content with full control over formatting */
    data class Custom(
        val formatter: (xLabel: String, yValue: Double) -> String
    ) : TooltipContent()
}

/**
 * Defines the style of the circular marker shown at the clicked point.
 */
sealed class MarkerStyle {
    /** Stroke (outline) circle marker */
    data class Stroke(val strokeWidth: Dp = 2.dp) : MarkerStyle()
    
    /** Solid (filled) circle marker */
    object Solid : MarkerStyle()
    
    /** No marker circle (only show tooltip) */
    object None : MarkerStyle()
}

/**
 * Format large numbers with K, M, B suffixes
 */
private fun formatValue(value: Double): String {
    return when {
        value >= 1_000_000_000 -> "${(value / 1_000_000_000).roundToOneDecimal()}B"
        value >= 1_000_000 -> "${(value / 1_000_000).roundToOneDecimal()}M"
        value >= 1_000 -> "${(value / 1_000).toLong()}K"
        else -> value.toLong().toString()
    }
}

/**
 * Round a Double to one decimal place
 */
private fun Double.roundToOneDecimal(): String {
    val rounded = kotlin.math.round(this * 10) / 10
    return if (rounded == rounded.toLong().toDouble()) {
        rounded.toLong().toString()
    } else {
        rounded.toString()
    }
}
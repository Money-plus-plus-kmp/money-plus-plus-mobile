package com.moneyplusplus.design_system.component.chart.constants

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Constants for tooltip rendering.
 * All tooltip-specific dimensions are centralized here.
 */
internal object TooltipConstants {
    
    /** Padding inside the tooltip box */
    val PADDING = 8.dp
    
    /** Corner radius for tooltip box (top-left, top-right, bottom-left) */
    val CORNER_RADIUS = 8.dp
    
    /** Smaller corner radius for bottom-right corner */
    val BOTTOM_RIGHT_CORNER_RADIUS = 2.dp
    
    /** Vertical offset between marker and tooltip */
    val OFFSET_ABOVE_MARKER = 2.dp
    
    /** Font size for tooltip text */
    val FONT_SIZE = 12.sp
}

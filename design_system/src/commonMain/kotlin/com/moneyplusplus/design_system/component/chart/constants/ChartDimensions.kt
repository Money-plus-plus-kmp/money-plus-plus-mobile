package com.moneyplusplus.design_system.component.chart.constants

import androidx.compose.ui.unit.dp

/**
 * Dimension constants for chart rendering.
 * All spacing, sizing, and layout values are centralized here.
 */
internal object ChartDimensions {
    
    // ============== Point & Line ==============
    /** Width allocated per data point for horizontal spacing */
    val POINT_WIDTH = 35.dp
    
    /** Stroke width for the main chart line */
    val LINE_STROKE_WIDTH = 3.dp
    
    // ============== Vertical Spacing ==============
    /** Bottom spacing for X-axis labels */
    val SPACING_Y = 50.dp
    
    /** Spacing between Y-axis text and chart area */
    val TEXT_SPACING = 10.dp
    
    /** Vertical offset for line positioning */
    val VERTICAL_OFFSET = 11.dp
    
    /** Minimum padding added to chart width */
    val MIN_WIDTH_PADDING = 20.dp
    
    // ============== Grid ==============
    /** Stroke width for grid lines */
    val GRID_LINE_WIDTH = 1.dp
    
    /** Y-axis alignment offset for grid lines */
    val GRID_Y_ALIGNMENT = 9.dp
    
    /** Dash interval for dashed grid lines (in pixels) */
    const val GRID_DASH_INTERVAL = 16f
    
    // ============== Markers ==============
    /** Radius of the selection marker circle */
    val MARKER_RADIUS = 4.dp
    
    /** Touch tolerance for detecting point selection */
    val TOUCH_TOLERANCE = 20.dp
    
    // ============== Shadow ==============
    /** Horizontal offset for shadow gradient end */
    val SHADOW_OFFSET = 40.dp
    
    /** Factor for gradient fill bottom position */
    const val GRADIENT_FILL_BOTTOM_FACTOR = 40f
    
    /** Alpha value for shadow gradient start color */
    const val SHADOW_ALPHA = 0.32f
    
    // ============== Container ==============
    /** Default height for the chart */
    val CHART_HEIGHT = 200.dp
    
    /** Padding below chart title */
    val TITLE_BOTTOM_PADDING = 16.dp
    
    // ============== X-Axis ==============
    /** Bottom offset for X-axis labels */
    val X_AXIS_LABEL_BOTTOM_OFFSET = 20.dp
}

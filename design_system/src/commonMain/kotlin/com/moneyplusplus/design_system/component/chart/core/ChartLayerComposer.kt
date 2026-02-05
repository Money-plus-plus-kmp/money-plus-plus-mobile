package com.moneyplusplus.design_system.component.chart.core

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
import com.moneyplusplus.design_system.component.chart.config.ChartConfig
import com.moneyplusplus.design_system.component.chart.constants.ChartDimensions
import com.moneyplusplus.design_system.component.chart.core.axis.XAxisRenderer.drawXAxisLabels
import com.moneyplusplus.design_system.component.chart.core.axis.YAxisRenderer.drawYAxisLabels
import com.moneyplusplus.design_system.component.chart.core.grid.GridRenderer.drawGridLines

/**
 * Composes all the chart layers (grid, axes).
 * This replaces the old BaseChartContainer with a clearer name.
 * 
 * Separation of concerns:
 * - This file orchestrates the drawing of grid and axis layers
 * - Individual renderers handle their specific drawing logic
 */
internal object ChartLayerComposer {
    
    /**
     * Draws all base chart layers (X-axis, Y-axis, grid).
     *
     * @param xAxisLabels Labels for the X-axis
     * @param textMeasurer Text measurer for layout calculations
     * @param upperValue Maximum Y-axis value
     * @param lowerValue Minimum Y-axis value
     * @param config Chart configuration
     * @param xRegionWidth Width of each X-axis region
     */
    @OptIn(ExperimentalTextApi::class)
    fun <T> DrawScope.composeChartLayers(
        xAxisLabels: List<T>,
        textMeasurer: TextMeasurer,
        upperValue: Float,
        lowerValue: Float,
        config: ChartConfig,
        xRegionWidth: Dp
    ) {
        // Draw X-axis labels
        drawXAxisLabels(
            labels = xAxisLabels,
            textMeasurer = textMeasurer,
            textStyle = config.styles.axisLabel,
            upperValue = upperValue,
            xRegionWidth = xRegionWidth
        )
        
        // Draw Y-axis labels
        drawYAxisLabels(
            upperValue = upperValue,
            lowerValue = lowerValue,
            textMeasurer = textMeasurer,
            spacing = ChartDimensions.SPACING_Y,
            textStyle = config.styles.axisLabel,
            yAxisRange = config.yAxisRange
        )
        
        // Draw grid lines
        drawGridLines(
            gridColor = config.colors.gridColor,
            yAxisRange = config.yAxisRange,
            upperValue = upperValue,
            textMeasurer = textMeasurer,
            showDashedLines = true
        )
    }
}

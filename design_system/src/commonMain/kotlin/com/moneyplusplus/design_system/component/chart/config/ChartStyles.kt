package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.moneyplusplus.design_system.theme.theme.Theme

/**
 * Text style configuration for the chart.
 */
data class ChartStyles(
    /** Style for axis labels (X and Y axis) */
    val axisLabel: TextStyle,
    /** Style for the chart title */
    val title: TextStyle
) {
    companion object {
        /**
         * Creates default chart styles based on the current theme.
         */
        @Composable
        fun defaults(
            axisLabel: TextStyle = Theme.typography.label.xSmall.copy(color = Theme.colorScheme.hint),
            title: TextStyle = Theme.typography.label.medium.copy(color = Theme.colorScheme.hint)
        ): ChartStyles = ChartStyles(
            axisLabel = axisLabel,
            title = title
        )
    }
}

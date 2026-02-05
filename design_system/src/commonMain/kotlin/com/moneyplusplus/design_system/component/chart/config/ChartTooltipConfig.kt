package com.moneyplusplus.design_system.component.chart.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.moneyplusplus.design_system.theme.theme.Theme

/**
 * Configuration for the chart tooltip.
 */
data class ChartTooltipConfig(
    /** Background color of the tooltip */
    val backgroundColor: Color,
    /** Text color inside the tooltip */
    val textColor: Color
) {
    companion object {
        /**
         * Creates default tooltip configuration based on the current theme.
         */
        @Composable
        fun defaults(
            backgroundColor: Color = Theme.colorScheme.surface.surface,
            textColor: Color = Theme.colorScheme.title
        ): ChartTooltipConfig = ChartTooltipConfig(
            backgroundColor = backgroundColor,
            textColor = textColor
        )
    }
}

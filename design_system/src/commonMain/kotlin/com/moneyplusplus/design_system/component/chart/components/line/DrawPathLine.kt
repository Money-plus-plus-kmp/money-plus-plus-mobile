package com.moneyplusplus.design_system.component.chart.components.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp

internal fun DrawScope.drawPathLineWrapper(
    data: List<Double>,
    lineColor: Color,
    strokePath: Path,
    animatedProgress: Animatable<Float, AnimationVector1D>,
    function: (Int) -> Unit,
) {
    data.indices.forEach { index ->
        function(index)
    }
    clipRect(right = size.width * animatedProgress.value) {
        drawPath(
            path = strokePath, color = lineColor, style = Stroke(
                width = 3.dp.toPx(), cap = StrokeCap.Round
            )
        )
    }
}

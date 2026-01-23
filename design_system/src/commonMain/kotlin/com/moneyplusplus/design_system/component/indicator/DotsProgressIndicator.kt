package com.moneyplusplus.design_system.component.indicator

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun DotsProgressIndicator(
    modifier: Modifier = Modifier,
    numberOfDots: Int = 3,
    animationDuration: Int = 800,
    progressDuration: Int = 200,
    dotShape: Shape = CircleShape,
    colors: List<Color> = listOf(
        Theme.colorScheme.stroke,
        Theme.colorScheme.stroke.copy(0.8f),
        Theme.colorScheme.stroke.copy(0.6f),
    ),
    dotSize: Dp = 5.dp,
    spaceBetween: Dp = 2.dp,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val totalDuration = animationDuration + (numberOfDots - 1) * progressDuration

    val animatedAlphas = (0 until numberOfDots).map { currentDotIndex ->
        val startTime = currentDotIndex * progressDuration
        infiniteTransition.animateFloat(
            initialValue = 0.1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = totalDuration
                    0.1f at startTime.coerceAtMost(totalDuration) using LinearEasing
                    1f at (startTime + progressDuration).coerceAtMost(totalDuration) using LinearEasing
                    0.1f at totalDuration
                }
            )
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween, Alignment.CenterHorizontally)
    ) {
        (0 until numberOfDots).forEach { currentDotIndex ->
            Box(
                Modifier
                    .size(dotSize)
                    .alpha(animatedAlphas[currentDotIndex].value)
                    .background(colors.getOrElse(currentDotIndex) { colors.last() }, dotShape)
            )
        }
    }
}
package com.moneyplusplus.design_system.component.chart.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

private const val LINE_ANIMATION_DURATION_MS = 2000
private const val ANIMATION_START_DELAY_MS = 1000L

@Composable
internal fun rememberChartAnimationProgress(
    animationEnabled: Boolean,
    dataKey: Any
): State<Float> {
    val animatable = remember { Animatable(if (animationEnabled) 0f else 1f) }

    LaunchedEffect(dataKey, animationEnabled) {
        if (animationEnabled) {
            animatable.snapTo(0f)
            delay(ANIMATION_START_DELAY_MS)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = LINE_ANIMATION_DURATION_MS,
                    easing = LinearEasing
                )
            )
        } else {
            animatable.snapTo(1f)
        }
    }

    return animatable.asState()
}

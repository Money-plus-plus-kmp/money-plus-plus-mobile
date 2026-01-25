package com.moneyplusplus.design_system.theme.animation

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween

fun <T> easingAnimation(
    durationMillis: Int = 300,
    easing: Easing = EaseInOut
): TweenSpec<T> {
    return tween(
        durationMillis = durationMillis,
        easing = easing
    )
}
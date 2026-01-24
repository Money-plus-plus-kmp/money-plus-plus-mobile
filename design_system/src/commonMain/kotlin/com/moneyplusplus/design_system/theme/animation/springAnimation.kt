package com.moneyplusplus.design_system.theme.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring

fun <T> springAnimation(
    dampingRatio: Float = Spring.DampingRatioLowBouncy,
    stiffness: Float = Spring.StiffnessMediumLow
): SpringSpec<T> {
    return spring(
        dampingRatio = dampingRatio,
        stiffness = stiffness
    )
}
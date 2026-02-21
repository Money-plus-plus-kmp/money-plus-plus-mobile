package com.moneyplusplus.presentation.statistics.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.animation.easingAnimation
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.domain.entity.MonthlyOverview

private val BarHeight = 20.dp
private val BarCornerRadius = 30.dp
private val BarBorderWidth = 0.5.dp
private val BarBackgroundBorderWidth = 0.5.dp
private val BarSpacing = 12.dp
private const val StrokeAlpha = 26
private const val AnimationDurationMillis = 600

private val IncomeGradientStart = Color(0xFF0496AD)
private val IncomeGradientEnd = Color(0xFF097C8E)
private val ExpenseGradientStart = Color(0xFFDC143C)
private val ExpenseGradientEnd = Color(0xFFA01A35)

private val IncomeGradient = Brush.linearGradient(
    colors = listOf(IncomeGradientStart, IncomeGradientEnd)
)
private val ExpenseGradient = Brush.linearGradient(
    colors = listOf(ExpenseGradientStart, ExpenseGradientEnd)
)

@Composable
internal fun MonthlyOverviewProgressBar(
    overview: MonthlyOverview,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ProgressBar(
            percentage = overview.incomePercentage,
            gradient = IncomeGradient,
        )

        Spacer(modifier = Modifier.height(BarSpacing))

        ProgressBar(
            percentage = overview.expensePercentage,
            gradient = ExpenseGradient,
        )
    }
}

@Composable
private fun ProgressBar(
    percentage: Float,
    gradient: Brush,
    modifier: Modifier = Modifier,
) {
    val barShape = remember { RoundedCornerShape(BarCornerRadius) }
    val strokeColor = Theme.colorScheme.stroke

    var targetRatio by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(percentage) {
        targetRatio = percentage
    }

    val animatedRatio by animateFloatAsState(
        targetValue = targetRatio,
        animationSpec = easingAnimation(durationMillis = AnimationDurationMillis),
        label = "progress_bar",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(BarHeight)
    ) {
        // Background track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(barShape)
                .background(strokeColor.copy(alpha = StrokeAlpha / 255f))
                .border(
                    width = BarBackgroundBorderWidth,
                    color = strokeColor.copy(alpha = StrokeAlpha / 255f),
                    shape = barShape,
                )
        )

        // Foreground progress
        if (animatedRatio > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedRatio)
                    .fillMaxHeight()
                    .clip(barShape)
                    .background(gradient)
                    .border(
                        width = BarBorderWidth,
                        color = strokeColor.copy(alpha = StrokeAlpha / 255f),
                        shape = barShape,
                    )
            )
        }
    }
}
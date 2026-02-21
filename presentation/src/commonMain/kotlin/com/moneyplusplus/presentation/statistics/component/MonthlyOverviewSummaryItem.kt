package com.moneyplusplus.presentation.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.statistics.util.AmountFormatter

private val IconContainerSize = 28.dp
private val IconContainerRadius = 8.dp
private val IconSize = 16.dp
private val IndicatorCircleSize = 6.dp
private val IconToContentSpacing = 8.dp
private val IndicatorToLabelSpacing = 4.dp

private val IncomeGradientStart = Color(0xFF0496AD)
private val IncomeGradientEnd = Color(0xFF097C8E)
private val ExpenseGradientStart = Color(0xFFDC143C)
private val ExpenseGradientEnd = Color(0xFFA01A35)

@Composable
internal fun MonthlyOverviewSummaryItem(
    icon: Painter,
    iconColor: Color,
    label: String,
    value: Double,
    currency: String,
    isIncome: Boolean,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (isIncome) {
        Theme.colorScheme.secondary.variant
    } else {
        Theme.colorScheme.primary.variant
    }

    val labelColor = if (isIncome) {
        Theme.colorScheme.secondary.secondary
    } else {
        Theme.colorScheme.primary.primary
    }

    val prefixColor = if (isIncome) {
        Theme.colorScheme.accent.green.green
    } else {
        Theme.colorScheme.primary.primary
    }

    val gradientColors = if (isIncome) {
        listOf(IncomeGradientStart, IncomeGradientEnd)
    } else {
        listOf(ExpenseGradientStart, ExpenseGradientEnd)
    }

    val prefix = if (isIncome) "+" else "-"
    val formattedValue = AmountFormatter.formatWithCurrency(value, currency)
    val titleColor = Theme.colorScheme.title

    val containerShape = remember { RoundedCornerShape(IconContainerRadius) }
    val indicatorGradient = remember(gradientColors) {
        Brush.verticalGradient(gradientColors)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Icon container
        Box(
            modifier = Modifier
                .size(IconContainerSize)
                .clip(containerShape)
                .background(containerColor),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(IconSize),
                colorFilter = ColorFilter.tint(iconColor),
            )
        }

        Spacer(modifier = Modifier.width(IconToContentSpacing))

        // Label + value
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            // Gradient dot + label
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(IndicatorCircleSize)
                        .clip(CircleShape)
                        .background(indicatorGradient),
                )

                Spacer(modifier = Modifier.width(IndicatorToLabelSpacing))

                Text(
                    text = label,
                    style = Theme.typography.label.xSmall,
                    color = labelColor,
                )
            }

            // +/- value with RichText
            val annotatedValue = buildAnnotatedString {
                withStyle(
                    SpanStyle(color = prefixColor)
                ) {
                    append(prefix)
                }
                withStyle(
                    SpanStyle(color = titleColor)
                ) {
                    append(formattedValue)
                }
            }

            Text(
                text = annotatedValue,
                style = Theme.typography.label.medium,
            )
        }
    }
}

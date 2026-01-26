package com.moneyplusplus.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme.colorScheme
import com.moneyplusplus.design_system.theme.theme.Theme.typography
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down_02
import money.presentation.generated.resources.arrow_up_02
import money.presentation.generated.resources.expenses
import money.presentation.generated.resources.income
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun IncomeExpenseChip(
    value: Double,
    currencyCode: String,
    modifier: Modifier = Modifier
) {

    val isPositive = (value >= 0)
    val icon = if (isPositive) Res.drawable.arrow_down_02 else Res.drawable.arrow_up_02
    val iconTint = if (isPositive) colorScheme.accent.green.green else colorScheme.accent.red.red
    val backgroundColor =
        if (isPositive) colorScheme.accent.green.greenVariant else colorScheme.accent.red.redVariant
    val label =
        if (isPositive) stringResource(Res.string.income) else stringResource(Res.string.expenses)
    val sign = if (isPositive) "+" else "−"
    val signColor = if (isPositive) colorScheme.accent.green.green else colorScheme.accent.red.red

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(colorScheme.surface.surfaceLow)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 4.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = iconTint,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(end = 10.dp)
        ) {
            Text(
                text = label,
                color = colorScheme.body,
                style = typography.label.xSmall,
            )

            MoneyAmount(
                value = if (isPositive) value else value * -1,
                sign = sign,
                signColor = signColor,
                currencyCode = currencyCode,
            )
        }
    }
}

@Composable
private fun MoneyAmount(
    value: Double,
    sign: String,
    signColor: Color,
    currencyCode: String,
) {
    Row {

        Text(
            text = sign,
            color = signColor,
            style = typography.label.medium
        )

        Text(
            text = value.toString(),
            color = colorScheme.title,
            style = typography.label.medium,
        )

        Text(
            text = " $currencyCode",
            color = colorScheme.title,
            style = typography.label.medium,
        )
    }
}

@Composable
@Preview
fun IncomeExpenseChipPreview() {
    MoneyTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IncomeExpenseChip(
                value = -1250.0,
                currencyCode = "USD"
            )

            IncomeExpenseChip(
                value = 1250.0,
                currencyCode = "USD",
            )
        }
    }
}
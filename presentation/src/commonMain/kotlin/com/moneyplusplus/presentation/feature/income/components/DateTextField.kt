package com.moneyplusplus.presentation.feature.income.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.textField.TextField
import kotlinx.datetime.LocalDate
import money.presentation.generated.resources.arrow_down_01
import money.presentation.generated.resources.calendar_03
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.date

@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    TextField(
        value = date.toString(),
        hint = stringResource(Res.string.date),
        leadingIcon = painterResource(Res.drawable.calendar_03),
        trailingIcon = painterResource(Res.drawable.arrow_down_01),
        readOnly = true,
        enabled = false,
        onValueChanged = {},
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    )
}
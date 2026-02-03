package com.moneyplusplus.presentation.feature.income.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.textField.TextField
import kotlinx.datetime.LocalDate
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down_01
import money.presentation.generated.resources.calendar_03
import org.jetbrains.compose.resources.painterResource


@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    date: LocalDate
) {
    TextField(
        value = date.toString(),
        hint = "Date",
        leadingIcon = painterResource(Res.drawable.calendar_03),
        trailingIcon = painterResource(Res.drawable.arrow_down_01),
        readOnly = true,
        onValueChanged = {},
        modifier = modifier
    )
}
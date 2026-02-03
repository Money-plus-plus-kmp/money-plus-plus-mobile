package com.moneyplusplus.presentation.feature.income.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.textField.MultiLineTextField
import kotlinx.datetime.LocalDate

@Composable
fun AddIncomeForm(
    modifier: Modifier = Modifier,
    amount: Int?,
    currency: String,
    date: LocalDate,
    note: String,
    setAmount: (Int?) -> Unit,
    setCurrency: (String) -> Unit,
    setNote: (String) -> Unit,
    onDateClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AmountTextField(
            amount = amount,
            currency = currency,
            setAmount = setAmount,
            setCurrency = setCurrency,
            modifier = Modifier.fillMaxWidth()
        )

        DateTextField(
            date = date,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDateClick)
        )

        MultiLineTextField(
            value = note,
            hint = "Note",
            onValueChanged = setNote
        )
    }
}
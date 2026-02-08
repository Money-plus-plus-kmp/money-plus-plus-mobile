package com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.moneyplusplus.design_system.component.button.TextButton
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Instant
@Composable
fun SalaryDayDatePicker(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onDaySelected: (Int) -> Unit
) {
    if (!isVisible) return
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(text = "OK", onClick = {
                val selectedMillis = datePickerState.selectedDateMillis
                if (selectedMillis != null) {
                    val day = Instant
                        .fromEpochMilliseconds(epochMilliseconds = selectedMillis)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                        .dayOfMonth

                    onDaySelected(day)
                }
            })
        },
        dismissButton = {
            TextButton(onClick = onDismiss, text = "Cancel")
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true)
@Composable
fun SalaryDayDatePickerPreview() {

    MoneyTheme {
        SalaryDayDatePicker(
            isVisible = true,
            onDismiss = {},
            onDaySelected = {}
        )
    }
}
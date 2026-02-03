package com.moneyplusplus.design_system.component.datetime

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.button.TextButton
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant


@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
) {
    Surface(
        modifier = modifier
    ) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(
                    text = "OK",
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            onDateSelected(LocalDate.fromEpochMillis(millis))
                        }
                        onDismissRequest()
                    }
                )
            },
            dismissButton = {
                TextButton(
                    text = "Cancel",
                    onClick = { onDismissRequest() }
                )
            }
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }
}

private fun LocalDate.Companion.fromEpochMillis(millis: Long): LocalDate {
    val instant = Instant.fromEpochMilliseconds(millis)
    return instant.toLocalDateTime(
        TimeZone.currentSystemDefault()
    ).date
}
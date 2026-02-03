package com.moneyplusplus.design_system.component.datetime

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.TextButton
import com.moneyplusplus.design_system.theme.theme.Theme
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
        modifier = modifier,
        color = Theme.colorScheme.surface.surfaceLow
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
                    },
                    modifier = Modifier.padding(12.dp)
                )
            },
            dismissButton = {
                TextButton(
                    text = "Cancel",
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(12.dp)
                )
            },
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = Theme.colorScheme.primary.primary,
                todayDateBorderColor = Theme.colorScheme.primary.primary
            )
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
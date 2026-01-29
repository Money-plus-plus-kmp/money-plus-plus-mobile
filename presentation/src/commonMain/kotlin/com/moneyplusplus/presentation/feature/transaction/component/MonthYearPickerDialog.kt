package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.TextButton
import com.moneyplusplus.design_system.component.chip.Chip
import com.moneyplusplus.design_system.component.dialog.AlertDialog
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_left_04
import money.presentation.generated.resources.cancel
import money.presentation.generated.resources.ic_arrow_right
import money.presentation.generated.resources.next
import money.presentation.generated.resources.ok
import money.presentation.generated.resources.previous
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MonthYearPickerDialog(
    visible: Boolean,
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    if (visible) {
        var selectedYear by remember { mutableIntStateOf(currentDate.year) }
        var selectedMonth by remember { mutableStateOf(currentDate.month) }

        AlertDialog(
            onDismissRequest = onDismiss,
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (selectedYear > 2025) {
                                selectedYear--
                            }
                        },
                        painter = painterResource(Res.drawable.arrow_left_04),
                        contentDescription = stringResource(Res.string.previous)
                    )

                    Text(
                        text = selectedYear.toString(),
                        style = Theme.typography.heading.medium,
                        color = Theme.colorScheme.title
                    )
                    Icon(
                        modifier = Modifier.clickable { selectedYear++ },
                        painter = painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = stringResource(Res.string.next)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(230.dp)
                ) {
                    items(Month.entries, key = { it.name }) { month ->
                        val isSelected = month == selectedMonth
                        Chip(
                            text = month.name.take(3),
                            isSelected = isSelected,
                            onClick = { selectedMonth = month },
                            modifier = Modifier.height(48.dp)
                        )
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        text = stringResource(Res.string.cancel),
                        hasIndication = false,
                        onClick = onDismiss
                    )


                    Spacer(modifier = Modifier.width(24.dp))
                    TextButton(
                        text = stringResource(Res.string.ok),
                        hasIndication = false,
                        onClick = {
                            val newDate = LocalDate(selectedYear, selectedMonth, 1)
                            onDateSelected(newDate)
                        })
                }
            }
        }
    }
}


@Preview
@Composable
fun MonthYearPickerDialogPreview() {
    MoneyTheme {
        MonthYearPickerDialog(
            visible = true,
            currentDate = LocalDate(2026, Month.JANUARY, 1),
            onDateSelected = {},
            onDismiss = {}
        )
    }

}
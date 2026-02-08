package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.formater.DateFormatter
import kotlinx.datetime.LocalDate
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down
import money.presentation.generated.resources.filter
import money.presentation.generated.resources.ic_arrow_down
import money.presentation.generated.resources.ic_filter
import money.presentation.generated.resources.transaction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionTopAppBar(
    date: LocalDate,
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {},
    onDateClick: () -> Unit = {},
) {
    AppBar(
        modifier = modifier
            .background(Theme.colorScheme.surface.surfaceLow),
        title = stringResource(Res.string.transaction),
        titleColor = Theme.colorScheme.title,
        trailingContent = {
            DateSelector(
                date = date,
                onDateClick = onDateClick
            )
            Icon(
                painter = painterResource(Res.drawable.ic_filter),
                contentDescription = stringResource(Res.string.filter),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.colorScheme.surface.surface)
                    .clickable(onClick = onFilterClick)
                    .padding(10.dp)
            )
        }
    )
}


@Composable
private fun DateSelector(
    date: LocalDate,
    modifier: Modifier = Modifier,
    onDateClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(end = 8.dp)
            .clip(
                shape = RoundedCornerShape(100.dp)
            )
            .background(Theme.colorScheme.surface.surface)
            .clickable(onClick = onDateClick)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = DateFormatter.formatMonthYear(date),
            style = Theme.typography.label.small,
            color = Theme.colorScheme.title
        )
        Icon(
            painter = painterResource(Res.drawable.ic_arrow_down),
            contentDescription = stringResource(Res.string.arrow_down),
        )
    }
}


@Preview(heightDp = 100, showBackground = true)
@Composable
private fun TransactionTopAppBarPreview() {
    MoneyTheme {
        TransactionTopAppBar(date = LocalDate(2026, 1, 1))
    }
}
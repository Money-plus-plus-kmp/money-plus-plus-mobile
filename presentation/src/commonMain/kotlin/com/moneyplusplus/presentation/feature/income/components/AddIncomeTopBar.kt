package com.moneyplusplus.presentation.feature.income.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.add_income
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.arrow_left_04
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddIncomeTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppBar(
        modifier = modifier,
        title = stringResource(Res.string.add_income),
        titleColor = Theme.colorScheme.title,
        leadingContent = {
            Icon(
                painter = painterResource(Res.drawable.arrow_left_04),
                contentDescription = stringResource(Res.string.arrow_left),
                modifier = Modifier.size(20.dp)
            )
        },
        onLeadingClick = onBackClick
    )
}
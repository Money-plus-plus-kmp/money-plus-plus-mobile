package com.moneyplusplus.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_icon
import money.presentation.generated.resources.back_button
import money.presentation.generated.resources.create_account
import money.presentation.generated.resources.money_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TopBar(
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(Res.drawable.arrow_icon),
                contentDescription = stringResource(Res.string.back_button)
            )
        }

        Text(
            text = stringResource(Res.string.create_account),
            style = Theme.typography.title.small,
            color = Theme.colorScheme.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )

        Image(
            painter = painterResource(Res.drawable.money_logo),
            contentDescription = stringResource(Res.string.money_logo),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        TopBar(
            onBackClick = {}
        )
    }
}

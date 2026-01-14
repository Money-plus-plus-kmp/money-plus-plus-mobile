package com.moneyplusplus.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.email
import money.presentation.generated.resources.mail_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = stringResource(Res.string.email),
                style = Theme.typography.label.medium,
                color = Theme.colorScheme.body
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.mail_icon),
                contentDescription = stringResource(Res.string.email)
            )
        },
        singleLine = true,
    )
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        EmailInput(
            value = "",
            onValueChange = {}
        )
    }
}

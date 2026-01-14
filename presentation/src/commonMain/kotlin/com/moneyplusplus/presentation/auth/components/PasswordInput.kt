package com.moneyplusplus.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.auth.utils.StarPasswordVisualTransformation
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.password
import money.presentation.generated.resources.password_icon
import money.presentation.generated.resources.password_visibility_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = stringResource(Res.string.password),
                style = Theme.typography.label.medium,
                color = Theme.colorScheme.body
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.password_icon),
                contentDescription = stringResource(Res.string.password)
            )
        },
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    painter = painterResource(Res.drawable.password_visibility_icon),
                    contentDescription = stringResource(Res.string.password)
                )
            }
        },
        visualTransformation =
            if (visible) VisualTransformation.None
            else StarPasswordVisualTransformation(),
        singleLine = true
    )
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        PasswordInput(
            value = "",
            onValueChange = {}
        )
    }
}

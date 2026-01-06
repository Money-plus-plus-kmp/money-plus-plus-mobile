package com.moneyplusplus.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.name
import money.presentation.generated.resources.username_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun UsernameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(Res.string.name)) },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.username_icon),
                contentDescription = stringResource(Res.string.name)
            )
        },
        singleLine = true
    )
}

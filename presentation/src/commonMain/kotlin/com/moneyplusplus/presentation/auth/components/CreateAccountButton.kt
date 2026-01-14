package com.moneyplusplus.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.create_account
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CreateAccountButton(
    enabled: Boolean,
    loading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            containerColor = Theme.colorScheme.primary.primary,
            contentColor = Theme.colorScheme.onPrimary.onPrimary,
            disabledContainerColor = Theme.colorScheme.disable,
            disabledContentColor = Theme.colorScheme.onPrimary.onPrimary
        )
    ) {
        Text(
            text = stringResource(Res.string.create_account),
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.onPrimary.onPrimary
        )
        if (loading) {
            CircularProgressIndicator(
                color = Theme.colorScheme.onPrimary.onPrimary,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
            )

        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        CreateAccountButton(
            enabled = true,
            loading = false,
            onClick = {}
        )
    }
}

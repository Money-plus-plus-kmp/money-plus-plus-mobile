package com.moneyplusplus.presentation.feature.forgetPassword.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.Button
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Email_text
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.ic_email
import money.presentation.generated.resources.send_reset_link
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ResetPasswordInputSection(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onEmailChanged: (String) -> Unit,
    onButtonClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        TextField(
            value = "",
            onValueChanged = { onEmailChanged(it) },
            hint = stringResource(Res.string.Email_text),
            leadingIcon = painterResource(Res.drawable.ic_email),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { onButtonClick() },
            shape = RoundedCornerShape(16.dp),
            containerColor = Theme.colorScheme.primary.primary,
            disabledContentColor = Theme.colorScheme.disable,
            isEnabled = isEnabled,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(Res.string.send_reset_link),
                style = Theme.typography.label.medium,
                color = Theme.colorScheme.onPrimary.onPrimary
            )
        }
    }
}

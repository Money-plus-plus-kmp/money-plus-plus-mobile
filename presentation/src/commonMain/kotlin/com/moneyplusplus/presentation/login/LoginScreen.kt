package com.moneyplusplus.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.OutlinedButton
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.googel
import money.presentation.generated.resources.heroicons_outline
import money.presentation.generated.resources.login_background
import money.presentation.generated.resources.mail_02
import money.presentation.generated.resources.square_lock_02
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
private fun LoginScreenContent() {
    Box {
        Image(
            painter = painterResource(Res.drawable.login_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LogoHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )

        LoginFormSection(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun LogoHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp, 45.dp)
    ) {
        Text(
            text = "++",
            style = Theme.typography.title.large,
            color = Theme.colorScheme.onPrimary.onPrimary,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            text = "Money",
            style = Theme.typography.title.large,
            color = Theme.colorScheme.onPrimary.onPrimary,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Composable
private fun LoginFormSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Theme.colorScheme.surface.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Welcome again!",
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Enter your credentials to access your account",
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            hint = "Email ",
            leadingIcon = painterResource(Res.drawable.mail_02),
            onValueChanged = { text = it },
            modifier = Modifier.padding(bottom = 12.dp)
        )

        TextField(
            value = text,
            hint = "Password",
            onValueChanged = { text = it },
            leadingIcon = painterResource(Res.drawable.square_lock_02),
            trailingIcon = painterResource(Res.drawable.heroicons_outline),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "Forget password?",
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.primary.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 100.dp)
        )

        PrimaryButton(
            text = "Login",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OrDivider()

        OutlinedButton(
            text = "Continue with Google",
            trailingIcon = painterResource(Res.drawable.googel),
            containerColor = Theme.colorScheme.surface.surfaceLow,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedButton(
            text = "Create new account",
            containerColor = Theme.colorScheme.surface.surfaceLow,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    }
}

@Composable
private fun HorizontalLine(
    modifier: Modifier = Modifier,
    color: Color = Theme.colorScheme.stroke,
    height: Dp = 1.dp
) {
    Box(
        modifier = modifier
            .height(height)
            .background(color)
    )
}

@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalLine(
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = "or",
            style = Theme.typography.label.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalLine(
            modifier = Modifier
                .weight(1f)
        )
    }
}


@Preview
@Composable
private fun LoginScreenContentPreview() {
    MoneyTheme {
        LoginScreenContent()
    }
}
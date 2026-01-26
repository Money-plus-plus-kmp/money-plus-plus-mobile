package com.moneyplusplus.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.button.OutlinedButton
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.base.collectEffect
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.continue_with_google
import money.presentation.generated.resources.create_new_account
import money.presentation.generated.resources.email_hint
import money.presentation.generated.resources.forget_password
import money.presentation.generated.resources.google
import money.presentation.generated.resources.heroicons_outline
import money.presentation.generated.resources.login
import money.presentation.generated.resources.login_background
import money.presentation.generated.resources.login_subtitle
import money.presentation.generated.resources.login_welcome
import money.presentation.generated.resources.logo_money
import money.presentation.generated.resources.logo_plus
import money.presentation.generated.resources.mail_02
import money.presentation.generated.resources.money
import money.presentation.generated.resources.or
import money.presentation.generated.resources.password_hint
import money.presentation.generated.resources.square_lock_02
import money.presentation.generated.resources.white_money
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateHome: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.effect.collectEffect { effect ->
        when (effect) {
            LoginEffect.NavigateToHome -> onNavigateHome()
        }
    }
    LoginScreenContent(
        state = state,
        intent = viewModel::handleIntent
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginState,
    intent: (LoginIntent) -> Unit
) {
    Box {
        Image(
            painter = painterResource(Res.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader(
                modifier = Modifier.padding(vertical = 80.dp)
            )
            LoginFormSection(
                state = state,
                intent = intent
            )
        }
    }
}

@Composable
private fun LogoHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(100.dp, 45.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.white_money),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LoginFormSection(
    modifier: Modifier = Modifier,
    state: LoginState,
    intent: (LoginIntent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Theme.colorScheme.surface.surface)
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(Res.string.login_welcome),
            style = Theme.typography.heading.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = stringResource(Res.string.login_subtitle),
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = state.email,
            hint = stringResource(Res.string.email_hint),
            leadingIcon = painterResource(Res.drawable.mail_02),
            onValueChanged = { intent(LoginIntent.EmailChanged(it)) },
            modifier = Modifier.padding(bottom = 12.dp),
            errorMessage = state.emailError?.let { stringResource(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = state.password,
            hint = stringResource(Res.string.password_hint),
            onValueChanged = { intent(LoginIntent.PasswordChanged(it)) },
            leadingIcon = painterResource(Res.drawable.square_lock_02),
            trailingIcon = if (state.isPasswordVisible)
                painterResource(Res.drawable.heroicons_outline)
            else
                painterResource(Res.drawable.heroicons_outline),
            showTrailingDivider = false,
            onTrailingIconClick = { intent(LoginIntent.TogglePasswordVisibility) },
            modifier = Modifier.padding(bottom = 12.dp),
            errorMessage = state.passwordError?.let { stringResource(it) },
            visualTransformation = if (state.isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { intent(LoginIntent.LoginClicked) }
            )
        )

        Text(
            text = stringResource(Res.string.forget_password),
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.primary.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        intent(LoginIntent.ForgetPasswordClicked)
                    }
                )
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = stringResource(Res.string.login),
            onClick = { intent(LoginIntent.LoginClicked) },
            isLoading = state.isLoading,
            isEnabled = state.canSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OrDivider()

        OutlinedButton(
            text = stringResource(Res.string.continue_with_google),
            trailingIcon = painterResource(Res.drawable.google),
            containerColor = Theme.colorScheme.surface.surfaceLow,
            onClick = { intent(LoginIntent.ContinueWithGoogleClicked) },
            isLoading = state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedButton(
            text = stringResource(Res.string.create_new_account),
            containerColor = Theme.colorScheme.surface.surfaceLow,
            onClick = { intent(LoginIntent.CreateNewAccountClicked) },
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
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
        HorizontalLine(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(Res.string.or),
            style = Theme.typography.label.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalLine(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    MoneyTheme {
        LoginScreenContent(
            state = LoginState(),
            intent = {}
        )
    }
}
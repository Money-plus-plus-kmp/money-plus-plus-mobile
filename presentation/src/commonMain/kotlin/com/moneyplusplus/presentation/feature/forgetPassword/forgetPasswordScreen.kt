package com.moneyplusplus.presentation.feature.forgetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.base.ObserveAsEffect
import com.moneyplusplus.presentation.feature.forgetPassword.component.ForgetPasswordHeader
import com.moneyplusplus.presentation.feature.forgetPassword.component.ResetPasswordInputSection
import com.moneyplusplus.presentation.feature.forgetPassword.component.TopAppBar
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.enter_your_email_to_reset_password
import money.presentation.generated.resources.forget_your_password
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ForgetPasswordScreen(
    viewModel: ForgetPasswordViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()


    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is ForgetPasswordEffect.NavigateBack -> onNavigateBack()
            is ForgetPasswordEffect.NavigateToLogin -> onNavigateBack()
            is ForgetPasswordEffect.ShowSnackbar -> {
               // TODO: Show snackbar
            }

        }

    }

    Content(
        isEnable = uiState.isEnableButton,
        onBackClick = { viewModel.handleIntent(ForgetPasswordIntent.OnBackClick) },
        onEmailChanged = { viewModel.handleIntent(ForgetPasswordIntent.OnEmailChanged(it)) },
        onButtonClick = { viewModel.handleIntent(ForgetPasswordIntent.OnSendResetLinkClick) },
        modifier = modifier
    )
}

@Composable
fun Content(
    isEnable: Boolean,
    onBackClick: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onBackClick = onBackClick
            )
        }
    )
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Theme.colorScheme.surface.surface),
        ) {
            ForgetPasswordHeader()

            Text(
                text = stringResource(Res.string.forget_your_password),
                style = Theme.typography.heading.medium,
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 16.dp,
                    bottom = 4.dp
                )
            )

            Text(
                text = stringResource(Res.string.enter_your_email_to_reset_password),
                style = Theme.typography.body.small,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 16.dp
                )

            )

            ResetPasswordInputSection(
                isEnabled = isEnable,
                onEmailChanged = onEmailChanged,
                onButtonClick = onButtonClick
            )
        }
    }
}


@Composable
@Preview()
fun ForgetPasswordPreview() {
    MoneyTheme {
        ForgetPasswordScreen(
            onNavigateBack = {},
        )
    }
}
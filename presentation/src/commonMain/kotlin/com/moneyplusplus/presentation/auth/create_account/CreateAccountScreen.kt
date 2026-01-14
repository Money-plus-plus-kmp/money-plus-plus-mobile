package com.moneyplusplus.presentation.auth.create_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.auth.components.CreateAccountButton
import com.moneyplusplus.presentation.auth.components.EmailInput
import com.moneyplusplus.presentation.auth.components.PasswordInput
import com.moneyplusplus.presentation.auth.components.TopBar
import com.moneyplusplus.presentation.auth.components.UsernameInput
import com.moneyplusplus.presentation.base.collectEffect
import com.moneyplusplus.presentation.base.collectState
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.create_new_account
import money.presentation.generated.resources.password_hint
import money.presentation.generated.resources.start_control_money
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavigateToAccountSetup: () -> Unit
) {
    val state = viewModel.collectState()

    viewModel.effect.collectEffect { effect ->
        when (effect) {
            CreateAccountEffect.NavigateBack -> onBackClick()
            is CreateAccountEffect.NavigateToAccountSetup -> onNavigateToAccountSetup()
            is CreateAccountEffect.ShowErrorSnackBar -> {}
        }
    }

    Scaffold(
        bottomBar = {
            CreateAccountButton(
                enabled = true,
                loading = false,
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { padding ->
        CreateAccountContent(
            modifier = Modifier.padding(padding),
            state = state,
            intent = { viewModel.handleIntent(it) }
        )
    }
}

@Composable
fun CreateAccountContent(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    intent: (CreateAccountIntent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {

        TopBar(
            onBackClick = {}
        )

        Text(
            text = stringResource(Res.string.create_new_account),
            style = Theme.typography.heading.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = stringResource(Res.string.start_control_money),
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 4.dp)
        )

        EmailInput(
            value = state.email,
            onValueChange = {
                intent(CreateAccountIntent.OnEmailChanged(it))
            },
            modifier = Modifier.padding(top = 24.dp)
        )

        UsernameInput(
            value = state.username,
            onValueChange = {
                intent(CreateAccountIntent.OnUsernameChanged(it))
            },
            modifier = Modifier.padding(top = 12.dp)
        )

        PasswordInput(
            value = state.password,
            onValueChange = {
                intent(CreateAccountIntent.OnPasswordChanged(it))
            },
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = stringResource(Res.string.password_hint),
            style = Theme.typography.label.small,
            color = Theme.colorScheme.accent.yellow.yellow,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        CreateAccountContent(
            state = CreateAccountState(),
            intent = {}
        )
    }
}

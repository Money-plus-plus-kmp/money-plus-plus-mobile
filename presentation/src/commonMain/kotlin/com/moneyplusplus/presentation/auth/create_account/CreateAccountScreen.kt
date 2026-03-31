package com.moneyplusplus.presentation.auth.create_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.base.collectEffect
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.back_button
import money.presentation.generated.resources.create_account
import money.presentation.generated.resources.create_new_account
import money.presentation.generated.resources.email
import money.presentation.generated.resources.mail_icon
import money.presentation.generated.resources.money_logo
import money.presentation.generated.resources.name
import money.presentation.generated.resources.password
import money.presentation.generated.resources.password_hint
import money.presentation.generated.resources.password_icon
import money.presentation.generated.resources.start_control_money
import money.presentation.generated.resources.username_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavigateToAccountSetup: () -> Unit,

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.effect.collectEffect { effect ->
        when (effect) {
            CreateAccountEffect.NavigateBack -> onBackClick()
            is CreateAccountEffect.NavigateToAccountSetup -> onNavigateToAccountSetup()
            is CreateAccountEffect.ShowErrorSnackBar -> {}
        }
    }

    CreateAccountScaffold(
        state = state,
        intent = viewModel::handleIntent
    )
}

@Composable
private fun CreateAccountScaffold(
    state: CreateAccountState,
    intent: (CreateAccountIntent) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                leadingContent = {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_left),
                        contentDescription = stringResource(Res.string.back_button)
                    )
                },
                onLeadingClick = { intent(CreateAccountIntent.OnBackClicked) },
                title = stringResource(Res.string.create_account),
                trailingContent = {
                    Icon(
                        painter = painterResource(Res.drawable.money_logo),
                        contentDescription = stringResource(Res.string.money_logo)
                    )
                }
            )
        },
        content = {
            CreateAccountContent(
                state = state,
                intent = intent,
            )
        },
        bottomBar = {
            PrimaryButton(
                onClick = {},
                isEnabled = true,
                isLoading = false,
                text = stringResource(Res.string.create_account),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    )
}

@Composable
private fun CreateAccountContent(
    modifier: Modifier = Modifier,
    state: CreateAccountState,
    intent: (CreateAccountIntent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {

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

        TextField(
            value = state.email,
            hint = stringResource(Res.string.email),
            onValueChanged = {
                intent(CreateAccountIntent.OnEmailChanged(it))
            },
            leadingIcon = painterResource(Res.drawable.mail_icon),
            modifier = Modifier.padding(top = 24.dp)
        )

        TextField(
            value = state.username,
            hint = stringResource(Res.string.password),
            onValueChanged = {
                intent(CreateAccountIntent.OnUsernameChanged(it))
            },
            leadingIcon = painterResource(Res.drawable.username_icon),
            modifier = Modifier.padding(top = 12.dp)
        )

        TextField(
            value = state.password,
            hint = stringResource(Res.string.name),
            onValueChanged = {
                intent(CreateAccountIntent.OnPasswordChanged(it))
            },
            leadingIcon = painterResource(Res.drawable.password_icon),
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
        CreateAccountScaffold(
            state = CreateAccountState(),
            intent = {}
        )
    }
}

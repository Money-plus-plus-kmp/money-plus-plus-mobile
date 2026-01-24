package com.moneyplusplus.presentation.auth.create_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.appBar.AppBarOptionContainer
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.base.collectEffect
import com.moneyplusplus.presentation.base.collectState
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_icon
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
    intent: (CreateAccountIntent) -> Unit,
    onNavigateToAccountSetup: () -> Unit,

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
        topBar = {
            AppBar(
                leadingContent = {
                    AppBarOptionContainer(
                        onClick = { intent(CreateAccountIntent.OnBackClicked) },
                        isBadgeVisible = true,
                        content = {
                            Icon(
                                painter = painterResource(Res.drawable.arrow_icon),
                                contentDescription = stringResource(Res.string.back_button)
                            )
                        }
                    )
                },
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
            )
        }
    )
}

@Composable
fun CreateAccountContent(
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
        CreateAccountContent(
            state = CreateAccountState(),
            intent = {}
        )
    }
}

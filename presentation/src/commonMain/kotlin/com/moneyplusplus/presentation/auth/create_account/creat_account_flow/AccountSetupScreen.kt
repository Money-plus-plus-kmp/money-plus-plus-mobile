package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import money.presentation.generated.resources.arrow_down_01
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.back_button
import money.presentation.generated.resources.calendar_03
import money.presentation.generated.resources.create_account
import money.presentation.generated.resources.money_01
import money.presentation.generated.resources.money_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AccountSetupScreen(
    viewModel: AccountSetupViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavigateToAccountSetup: () -> Unit,

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.effect.collectEffect { effect ->
        when (effect) {

            else -> {}
        }
    }

    AccountSetupScaffold(
        state = state,
        intent = viewModel::handleIntent
    )
}

@Composable
private fun AccountSetupScaffold(
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
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
                onLeadingClick = { },
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
            AccountSetupContent(
                state = state,
                intent = intent,
            )
        },
        bottomBar = {
            PrimaryButton(
                onClick = {},
                isEnabled = true,
                isLoading = false,
                text = "Next",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    )
}

@Composable
private fun AccountSetupContent(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        StepProgressBar(
            currentStep = 0,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Step 1 of 3",
            style = Theme.typography.label.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Let’s set up your account",
            style = Theme.typography.heading.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "Set Salary",
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        TextField(
            value = "",
            hint = "Currency",
            readOnly = true,
            enabled = true,
            onValueChanged = { },
            leadingIcon = painterResource(Res.drawable.money_01),
            trailingIcon = painterResource(Res.drawable.arrow_down_01),
            showTrailingDivider = false,
            onTrailingIconClick = {},
            modifier = Modifier.padding(bottom = 12.dp),
        )

        TextField(
            value = state.salary,
            hint = "Salary",
            onValueChanged = { },
            leadingIcon = painterResource(Res.drawable.money_01),
            showTrailingDivider = false,
            onTrailingIconClick = {},
            modifier = Modifier.padding(bottom = 12.dp),
        )

        TextField(
            value = "",
            hint = "Salary day",
            readOnly = true,
            enabled = true,
            onValueChanged = { },
            leadingIcon = painterResource(Res.drawable.calendar_03),
            trailingIcon = painterResource(Res.drawable.arrow_down_01),
            showTrailingDivider = false,
            onTrailingIconClick = {},
            modifier = Modifier.padding(bottom = 12.dp),
        )


    }
}

@Composable
fun StepProgressBar(
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(6.dp)
                    .background(
                        color = if (index == currentStep)
                            Theme.colorScheme.title
                        else
                            Theme.colorScheme.stroke,
                        shape = CircleShape
                    )
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        AccountSetupScaffold(
            state = AccountSetupState(),
            intent = {}
        )
    }
}
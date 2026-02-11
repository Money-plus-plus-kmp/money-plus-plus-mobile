package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.bottomSheet.BottomSheet
import com.moneyplusplus.design_system.component.button.OutlinedButton
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.CurrencyBottomSheetContent
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.SalaryDayPickerOverlay
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.StepOneContent
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.StepThreeContent
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.StepTwoContent
import com.moneyplusplus.presentation.base.collectEffect
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.back_button
import money.presentation.generated.resources.create_account
import money.presentation.generated.resources.money_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountSetupScreen(
    viewModel: AccountSetupViewModel = koinViewModel(),
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
            if (state.currentStep == AccountSetupStep.STEP_THREE) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrimaryButton(
                        onClick = { intent(AccountSetupIntent.FinishClicked) },
                        isEnabled = state.isFormValidForCurrentStep,
                        isLoading = false,
                        text = "Finish setup",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                    OutlinedButton(
                        onClick = {},
                        text = "Add another category",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            } else {
                PrimaryButton(
                    onClick = { intent(AccountSetupIntent.NextClicked) },
                    isEnabled = state.isFormValidForCurrentStep,
                    isLoading = false,
                    text = "Next",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        },
        overlays = {
            bottomSheet(isVisible = state.isCurrencyBottomSheetVisible) {
                BottomSheet(
                    isVisible = it,
                    onDismissRequest = { intent(AccountSetupIntent.DismissCurrencyBottomSheet) },
                    sheetContent = {
                        CurrencyBottomSheetContent(
                            state = state,
                            intent = intent
                        )
                    })
            }
            bottomSheet(isVisible = state.isSalaryDayPickerVisible) {
                SalaryDayPickerOverlay(
                    state = state,
                    intent = intent,
                    isVisible = it
                )
            }
        }
    )
}

@Composable
private fun AccountSetupContent(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
) {
    when (state.currentStep) {
        AccountSetupStep.STEP_ONE -> StepOneContent(
            modifier = modifier,
            state = state,
            intent = intent
        )

        AccountSetupStep.STEP_TWO -> StepTwoContent(
            modifier = modifier,
            state = state,
            intent = intent
        )

        AccountSetupStep.STEP_THREE -> StepThreeContent(
            modifier = modifier,
            state = state,
            intent = intent
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        AccountSetupScaffold(
            state = AccountSetupState(
                currentStep = AccountSetupStep.STEP_THREE
            ),
            intent = {}
        )
    }
}
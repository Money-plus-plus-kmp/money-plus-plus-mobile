package com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.AccountSetupIntent
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.AccountSetupState
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.AccountSetupStep
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.money_01
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StepTwoContent(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(horizontal = 16.dp)
    )
    {
        StepsHeader(
            currentStep = state.currentStep.ordinal,
            description = "How much money do you have right now?"
        )

        TextField(
            value = state.currentBalance,
            hint = "Current balance",
            onValueChanged = { intent(AccountSetupIntent.CurrentBalanceChanged(newBalance = it)) },
            leadingIcon = painterResource(Res.drawable.money_01),
            showTrailingDivider = false,
            modifier = Modifier
                .padding(bottom = 12.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StepTwoContentPreview() {
    MoneyTheme {
        StepTwoContent(
            state = AccountSetupState(
                currentBalance = "12000",
                currentStep = AccountSetupStep.STEP_TWO
            ),
            intent = {}
        )
    }
}

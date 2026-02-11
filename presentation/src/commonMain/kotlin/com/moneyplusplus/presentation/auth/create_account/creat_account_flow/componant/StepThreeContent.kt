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
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun StepThreeContent(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    )
    {
        StepsHeader(
            currentStep = state.currentStep.ordinal,
            description = "Where do you usually spend your money?"
        )

        TextField(
            value = "",
            hint = "Category name",
            onValueChanged = { },
            showTrailingDivider = false,
            modifier = Modifier
                .padding(bottom = 12.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StepThreeContentPreview() {
    MoneyTheme {
        StepThreeContent(
            state = AccountSetupState(
                currentBalance = "12000",
                currentStep = AccountSetupStep.STEP_THREE
            ),
            intent = {}
        )
    }
}
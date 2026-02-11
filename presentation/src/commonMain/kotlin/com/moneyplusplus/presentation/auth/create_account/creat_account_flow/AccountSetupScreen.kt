package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.bottomSheet.BottomSheet
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.SalaryDayDatePicker
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant.StepProgressBar
import com.moneyplusplus.presentation.auth.create_account.selectionTriangle
import com.moneyplusplus.presentation.base.collectEffect
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down_01
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.back_button
import money.presentation.generated.resources.calendar_03
import money.presentation.generated.resources.create_account
import money.presentation.generated.resources.ic_cancel
import money.presentation.generated.resources.money_01
import money.presentation.generated.resources.money_logo
import money.presentation.generated.resources.search_01
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
            PrimaryButton(
                onClick = {},
                isEnabled = state.isFormValid,
                isLoading = false,
                text = "Next",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
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
private fun SalaryDayPickerOverlay(
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit,
    isVisible: Boolean
) {
    SalaryDayDatePicker(
        isVisible = isVisible,
        onDismiss = {
            intent(
                AccountSetupIntent.SalaryDaySelected(
                    state.salaryDay ?: 1
                )
            )
        },
        onDaySelected = { day ->
            intent(AccountSetupIntent.SalaryDaySelected(day))
        }
    )
}

@Composable
private fun CurrencyBottomSheetContent(
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 24.dp)
                .padding(end = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Currency",
                    style = Theme.typography.title.small,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(Res.drawable.ic_cancel),
                    contentDescription = "cancel bottom sheet icon",
                    modifier = Modifier.clickable {
                        intent(AccountSetupIntent.DismissCurrencyBottomSheet)
                    }
                )
            }

            MyDivider(modifier = Modifier.padding(bottom = 16.dp))

            TextField(
                value = "",
                hint = "Search ...",
                readOnly = true,
                enabled = true,
                onValueChanged = { },
                leadingIcon = painterResource(Res.drawable.search_01),
                showTrailingDivider = false,
                onTrailingIconClick = {},
                modifier = Modifier.padding(bottom = 12.dp),
            )

            LazyColumn {
                items(state.currencies) { currency ->
                    CurrencyItem(
                        name = currency.name,
                        code = currency.code,
                        country = currency.country,
                        isSelected = state.selectedCurrency == currency,
                        onClick = { intent(AccountSetupIntent.SelectCurrencyItem(currency)) }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Theme.colorScheme.surface.surfaceLow)
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            PrimaryButton(
                onClick = { intent(AccountSetupIntent.DismissCurrencyBottomSheet) },
                text = "Select",
                isEnabled = state.selectedCurrency != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun CurrencyItem(
    name: String,
    code: String,
    country: String,
    isSelected: Boolean,
    onClick: () -> Unit = {}
) {
    val itemSelectedColor = if (isSelected)
        Theme.colorScheme.primary.primary
    else
        Theme.colorScheme.title

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .selectionTriangle(
                isVisible = isSelected,
                color = Theme.colorScheme.primary.primary
            )
            .clickable { onClick() }
            .padding(start = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = Theme.typography.label.medium,
                color = itemSelectedColor,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = code,
                style = Theme.typography.label.medium,
                color = itemSelectedColor
            )
        }

        Text(
            text = country,
            style = Theme.typography.label.small,
            color = if (isSelected)
                Theme.colorScheme.primary.primary
            else
                Theme.colorScheme.body
        )
        MyDivider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
private fun MyDivider(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(0.8.dp)
            .background(
                color = Theme.colorScheme.stroke,
                shape = CircleShape
            )
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
            value = state.selectedCurrency?.displayName ?: "",
            hint = "Currency",
            readOnly = true,
            enabled = true,
            onValueChanged = { },
            leadingIcon = painterResource(Res.drawable.money_01),
            trailingIcon = painterResource(Res.drawable.arrow_down_01),
            onTrailingIconClick = { intent(AccountSetupIntent.ClickCurrencyArrow) },
            showTrailingDivider = false,
            modifier = Modifier
                .padding(bottom = 12.dp),
        )

        TextField(
            value = state.salary,
            hint = "Salary",
            onValueChanged = { intent(AccountSetupIntent.SalaryChanged(newSalary = it)) },
            leadingIcon = painterResource(Res.drawable.money_01),
            showTrailingDivider = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(bottom = 12.dp),
        )

        TextField(
            value = state.salaryDayText,
            hint = "Salary day",
            readOnly = true,
            enabled = true,
            onValueChanged = { },
            leadingIcon = painterResource(Res.drawable.calendar_03),
            trailingIcon = painterResource(Res.drawable.arrow_down_01),
            showTrailingDivider = false,
            onTrailingIconClick = { intent(AccountSetupIntent.SalaryDayClicked) },
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable { intent(AccountSetupIntent.SalaryDayClicked) },
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoneyTheme {
        AccountSetupScaffold(
            state = AccountSetupState(
                isCurrencyBottomSheetVisible = true
            ),
            intent = {}
        )
    }
}
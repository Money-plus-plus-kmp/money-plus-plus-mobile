package com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.AccountSetupIntent
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.AccountSetupState
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.CurrencyUiModel
import com.moneyplusplus.presentation.auth.create_account.creat_account_flow.displayName
import com.moneyplusplus.presentation.auth.create_account.selectionTriangle
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_down_01
import money.presentation.generated.resources.calendar_03
import money.presentation.generated.resources.ic_cancel
import money.presentation.generated.resources.money_01
import money.presentation.generated.resources.search_01
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StepOneContent(
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
            description = "Set Salary"
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


@Composable
fun SalaryDayPickerOverlay(
    state: AccountSetupState,
    intent: (AccountSetupIntent) -> Unit,
    isVisible: Boolean
) {
    SalaryDayDatePicker(
        isVisible = isVisible,
        onDismiss = { intent(AccountSetupIntent.SalaryDaySelected(day = state.salaryDay ?: 1)) },
        onDaySelected = { day -> intent(AccountSetupIntent.SalaryDaySelected(day)) }
    )
}

@Composable
fun CurrencyBottomSheetContent(
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
                modifier = Modifier.padding(bottom = 12.dp, start = 16.dp),
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

            MyDivider(modifier = Modifier.padding(bottom = 16.dp, start = 16.dp))

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
fun CurrencyItem(
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
fun MyDivider(
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

@Preview(showBackground = true)
@Composable
fun StepOneContentPreview() {
    MoneyTheme {
        StepOneContent(
            state = AccountSetupState(
                salary = "5000",
                salaryDay = 15,
                salaryDayText = "15",
                selectedCurrency = CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                currencies = listOf(
                    CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                    CurrencyUiModel("Egyptian Pound", "Egypt", "EGP"),
                    CurrencyUiModel("Syrian Pound", "Syria", "SYP"),
                    CurrencyUiModel("Jordanian Dinar", "Jordan", "JOD"),
                    CurrencyUiModel("Kuwaiti Dinar", "Kuwait", "KWD"),
                    CurrencyUiModel("Bahraini Dinar", "Bahrain", "BHD"),
                    CurrencyUiModel("Omani Rial", "Oman", "OMR")
                )
            ),
            intent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyBottomSheetPreview() {
    MoneyTheme {
        CurrencyBottomSheetContent(
            state = AccountSetupState(
                selectedCurrency = CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                currencies = listOf(
                    CurrencyUiModel("Iraqi Dinar", "Iraq", "IQD"),
                    CurrencyUiModel("Egyptian Pound", "Egypt", "EGP"),
                    CurrencyUiModel("Syrian Pound", "Syria", "SYP"),
                    CurrencyUiModel("Jordanian Dinar", "Jordan", "JOD"),
                    CurrencyUiModel("Kuwaiti Dinar", "Kuwait", "KWD"),
                    CurrencyUiModel("Bahraini Dinar", "Bahrain", "BHD"),
                    CurrencyUiModel("Omani Rial", "Oman", "OMR")
                )
            ),
            intent = {}
        )
    }
}

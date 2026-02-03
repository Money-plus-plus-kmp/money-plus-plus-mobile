package com.moneyplusplus.presentation.feature.income.screens.addincome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.datetime.DatePicker
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.base.ObserveAsEffect
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddIncomeScreen(
    viewModel: AddIncomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is AddIncomeEffect.NavigateBack -> {} //TODO navigate back
            is AddIncomeEffect.ShowError -> {} //TODO navigate to login
            is AddIncomeEffect.ShowSuccess -> {} //TODO show snackbar
        }
    }

    AddIncomeContent(
        state = state,
        onIntent = viewModel::handleIntent
    )

}

@Composable
private fun AddIncomeContent(
    state: AddIncomeState,
    onIntent: (AddIncomeIntent) -> Unit
) {

    DatePicker(
        onDateSelected = { onIntent(AddIncomeIntent.SetDate(it)) },
        onDismissRequest = { onIntent(AddIncomeIntent.OnDatePickerDismiss) }
    )
}

@Composable
@Preview()
private fun PreviewAddIncomeScreen() {
    MoneyTheme {
        AddIncomeScreen()
    }
}

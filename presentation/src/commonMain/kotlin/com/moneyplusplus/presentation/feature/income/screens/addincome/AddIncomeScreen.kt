package com.moneyplusplus.presentation.feature.income.screens.addincome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.datetime.DatePicker
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.snackbar.LocalMSnackbarState
import com.moneyplusplus.presentation.base.ObserveAsEffect
import com.moneyplusplus.presentation.feature.income.components.AddIncomeForm
import com.moneyplusplus.presentation.feature.income.components.AddIncomeTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddIncomeScreen(
    viewModel: AddIncomeViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackBar = LocalMSnackbarState.current

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is AddIncomeEffect.NavigateBack -> { navigateBack() }
            is AddIncomeEffect.ShowError -> { snackBar.showError("Error: Failed to Add Income!") }
            is AddIncomeEffect.ShowSuccess -> { snackBar.showSuccess("Income Added Successfully!") }
        }
    }

    AddIncomeContent(
        state = state,
        onIntent = viewModel::handleIntent,
    )

}

@Composable
private fun AddIncomeContent(
    state: AddIncomeState,
    onIntent: (AddIncomeIntent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        fullScreen = true,
        topBar = { AddIncomeTopBar(onBackClick = { onIntent(AddIncomeIntent.OnBackClick) }) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AddIncomeForm(
                    amount = state.amount,
                    currency = state.currencyCode,
                    date = state.date,
                    note = state.note,
                    setAmount = { onIntent(AddIncomeIntent.SetAmount(it)) },
                    setNote = { onIntent(AddIncomeIntent.SetNote(it)) },
                    onDateClick = { onIntent(AddIncomeIntent.OnDateClick) }
                )

                PrimaryButton(
                    text = "Add",
                    isEnabled = state.isAddEnabled,
                    onClick = { onIntent(AddIncomeIntent.OnAddIncomeClick) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(state.isDatePickerVisible) {
                DatePicker(
                    onDateSelected = { onIntent(AddIncomeIntent.SetDate(it)) },
                    onDismissRequest = { onIntent(AddIncomeIntent.OnDatePickerDismiss) }
                )
            }
        }

    }

}
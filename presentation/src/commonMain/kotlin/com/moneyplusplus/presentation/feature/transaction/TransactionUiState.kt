package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.presentation.base.UiState
import com.moneyplusplus.presentation.model.CategoryUiModel
import com.moneyplusplus.presentation.model.TransactionUiModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.all
import money.presentation.generated.resources.expenses
import money.presentation.generated.resources.incomes
import org.jetbrains.compose.resources.StringResource
import kotlin.time.Clock

data class TransactionUiState(
    val transactions: List<TransactionUiModel> = emptyList(),
    val allTransactions: List<TransactionUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val typeFilter: TransactionTypeFilter = TransactionTypeFilter.ALL,
    val date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val showCategoriesFilterBottomSheet: Boolean = false,
    val showAddTransactionBottomSheet: Boolean = false,
    val categories: List<CategoryUiModel> = emptyList(),
    val selectedCategoryIds: List<String> = emptyList(),
    val showDatePickerDialog: Boolean = false
) : UiState{
    enum class TransactionTypeFilter(val labelResource: StringResource) {
        ALL(Res.string.all),
        INCOMES(Res.string.incomes),
        EXPENSES(Res.string.expenses)
    }
    enum class ContentState {
        LOADING,
        EMPTY,
        CONTENT
    }
}


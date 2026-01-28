package com.moneyplusplus.presentation.feature.transaction

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.bottomSheet.BottomSheet
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.presentation.feature.transaction.component.CategoryFilterBottomSheet
import com.moneyplusplus.presentation.feature.transaction.component.EmptyTransactionsView
import com.moneyplusplus.presentation.feature.transaction.component.MonthYearPickerDialog
import com.moneyplusplus.presentation.feature.transaction.component.TransactionItem
import com.moneyplusplus.presentation.feature.transaction.component.TransactionTopAppBar
import com.moneyplusplus.presentation.feature.transaction.component.TransactionTypeFilterSection
import com.moneyplusplus.presentation.model.CategoryUiModel
import com.moneyplusplus.presentation.model.TransactionUiModel
import kotlinx.datetime.LocalDate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel = koinViewModel(),

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            //todo handle effects here  whenever we needed
        }
    }

    TransactionScreenContent(
        transactions = state.transactions,
        date = state.date!!,
        selectedTransactionType = state.typeFilter,
        allCategories = state.categories,
        selectedCategoryIds = state.selectedCategoryIds,
        showCategoriesFilterBottomSheet = state.showCategoriesFilterBottomSheet,
        showDatePickerDialog = state.showDatePickerDialog,
        onFilterClick = { viewModel.handleIntent(TransactionIntent.OnFilterClick) },
        onDateClick = { viewModel.handleIntent(TransactionIntent.OnDateClick) },
        onTransactionTypeClick = { transactionType ->
            viewModel.handleIntent(
                TransactionIntent.OnTransactionTypeClick(transactionType)
            )
        },
        onAddTransactionClick = {
            viewModel.handleIntent(TransactionIntent.OnAddTransactionClick)
        },
        onApplyFilterClick = { selectedCategoryIds ->
            viewModel.handleIntent(
                TransactionIntent.OnApplyFilterClick(selectedCategoryIds)
            )
        },
        onDismissCategorySheet = {
            viewModel.handleIntent(TransactionIntent.OnFilterSheetDismissed)
        },
        onDateSelected = { date ->
            viewModel.handleIntent(TransactionIntent.OnDateSelected(date))
        },
        onDatePickerDialogDismiss = {
            viewModel.handleIntent(TransactionIntent.OnAddTransactionSheetDismissed)
        },
        currentDate = state.date!!,

        modifier = modifier
    )
}

@Composable
private fun TransactionScreenContent(
    transactions: List<TransactionUiModel>,
    selectedTransactionType: TransactionTypeFilter,
    date: LocalDate,
    onDateClick: () -> Unit,
    onFilterClick: () -> Unit,
    allCategories: List<CategoryUiModel>,
    selectedCategoryIds: List<String>,
    showCategoriesFilterBottomSheet: Boolean,
    showDatePickerDialog: Boolean,
    currentDate: LocalDate,
    onApplyFilterClick: (List<String>) -> Unit,
    onDismissCategorySheet: () -> Unit,
    onTransactionTypeClick: (TransactionTypeFilter) -> Unit,
    onAddTransactionClick: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onDatePickerDialogDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TransactionTopAppBar(
                date = date,
                onFilterClick = onFilterClick,
                onDateClick = onDateClick

            )
        },
        overlays = {
            bottomSheet(
                isVisible = showCategoriesFilterBottomSheet,
            ) {
                BottomSheet(
                    isVisible = showCategoriesFilterBottomSheet,
                    onDismissRequest = onDismissCategorySheet,
                    skipPartiallyExpanded = true,
                    paddingFromTop = 100.dp
                ) { closeSheet ->
                    CategoryFilterBottomSheet(
                        allCategories = allCategories,
                        selectedCategoryIds = selectedCategoryIds,
                        onApplyClick = { ids ->
                            onApplyFilterClick(ids)
                            closeSheet()
                        },
                        onDismiss = closeSheet
                    )
                }
            }
            dialog(
                isVisible = showDatePickerDialog,
            ) {
                MonthYearPickerDialog(
                    visible = showDatePickerDialog,
                    currentDate = currentDate,
                    onDateSelected = onDateSelected,
                    onDismiss = onDatePickerDialogDismiss
                )
            }


        }
    ) {

        AnimatedContent(
            targetState = transactions.isNotEmpty(),
            transitionSpec = {
                (fadeIn() + slideInVertically { 50 }).togetherWith(fadeOut())
            }
        ) { hasData ->
            if (hasData) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        TransactionTypeFilterSection(
                            onTransactionTypeClick = onTransactionTypeClick,
                            selectedTransactionType = selectedTransactionType
                        )
                    }
                    if (transactions.isEmpty()) {
                        item {
                            EmptyTransactionsView(
                                onAddTransactionClick = onAddTransactionClick,
                            )
                        }
                    } else {
                        items(
                            items = transactions,
                            key = { it.id }) { transaction ->
                            TransactionItem(
                                transaction = transaction,
                                modifier = Modifier.animateItem(
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessMediumLow
                                    )
                                )
                            )
                        }
                    }

                }
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    EmptyTransactionsView()
                }
            }
        }
    }
}





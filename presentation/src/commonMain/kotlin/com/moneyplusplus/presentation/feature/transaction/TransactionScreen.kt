package com.moneyplusplus.presentation.feature.transaction

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.bottomSheet.BottomSheet
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.feature.transaction.component.CategoryFilterBottomSheet
import com.moneyplusplus.presentation.feature.transaction.component.EmptyTransactionsView
import com.moneyplusplus.presentation.feature.transaction.component.MonthYearPickerDialog
import com.moneyplusplus.presentation.feature.transaction.component.TransactionItem
import com.moneyplusplus.presentation.feature.transaction.component.TransactionLoadingItem
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
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            //todo handle effects here  whenever we needed
        }
    }
    LaunchedEffect(state.typeFilter) {
        listState.animateScrollToItem(0)
    }

    TransactionScreenContent(
        isLoading = state.isLoading,
        transactions = state.transactions,
        date = state.date,
        listState = listState,
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
            viewModel.handleIntent(TransactionIntent.OnDatePickerDialogDismiss)
        },
        currentDate = state.date,

        modifier = modifier
    )
}

@Composable
private fun TransactionScreenContent(
    isLoading: Boolean,
    transactions: List<TransactionUiModel>,
    selectedTransactionType: TransactionUiState.TransactionTypeFilter,
    date: LocalDate,
    listState: LazyListState,
    onDateClick: () -> Unit,
    onFilterClick: () -> Unit,
    allCategories: List<CategoryUiModel>,
    selectedCategoryIds: List<String>,
    showCategoriesFilterBottomSheet: Boolean,
    showDatePickerDialog: Boolean,
    currentDate: LocalDate,
    onApplyFilterClick: (List<String>) -> Unit,
    onDismissCategorySheet: () -> Unit,
    onTransactionTypeClick: (TransactionUiState.TransactionTypeFilter) -> Unit,
    onAddTransactionClick: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onDatePickerDialogDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        statusBarColor = Theme.colorScheme.surface.surfaceLow,
        topBar = {
            TransactionTopAppBar(
                date = date,
                onFilterClick = onFilterClick,
                onDateClick = onDateClick
            )
            TransactionTypeFilterSection(
                onTransactionTypeClick = onTransactionTypeClick,
                selectedTransactionType = selectedTransactionType,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
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
        val currentState = when {
            isLoading && transactions.isEmpty() -> TransactionUiState.ContentState.LOADING
            transactions.isEmpty() -> TransactionUiState.ContentState.EMPTY
            else -> TransactionUiState.ContentState.CONTENT
        }
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                (fadeIn(animationSpec = tween(300)) +
                        slideInHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMediumLow
                            )
                        ) { 50 }
                        )
                    .togetherWith(fadeOut(animationSpec = tween(300)))
            },
            contentKey = { it },
        ) { state ->
            when (state) {
                TransactionUiState.ContentState.LOADING -> {
                    Column(Modifier.fillMaxSize().padding(16.dp)) {
                        repeat(20) {
                            TransactionLoadingItem()
                        }
                    }
                }

                TransactionUiState.ContentState.EMPTY -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        EmptyTransactionsView(onAddTransactionClick = onAddTransactionClick)
                    }
                }

                TransactionUiState.ContentState.CONTENT -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        state = listState
                    )
                    {


                        items(
                            items = transactions,
                            key = { it.id }) { transaction ->
                            TransactionItem(
                                transaction = transaction,
                                modifier = Modifier.animateItem(
                                    fadeInSpec = tween(300, easing = EaseInExpo),
                                    fadeOutSpec = tween(300, easing = EaseOutExpo),
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }

    }
}
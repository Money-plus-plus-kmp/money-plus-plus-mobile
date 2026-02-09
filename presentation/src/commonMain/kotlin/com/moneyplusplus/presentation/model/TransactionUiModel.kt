package com.moneyplusplus.presentation.model

data class TransactionUiModel(
    val id: String,
    val title: String,
    val amount: String,
    val dateFormated: String,
    val isExpense: Boolean,
    val categoryId: String
)


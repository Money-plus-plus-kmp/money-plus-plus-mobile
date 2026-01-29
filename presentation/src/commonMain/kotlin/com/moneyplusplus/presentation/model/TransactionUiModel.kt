package com.moneyplusplus.presentation.model

import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.entity.TransactionType
import com.moneyplusplus.presentation.formater.formatAmount
import com.moneyplusplus.presentation.formater.formatDate

data class TransactionUiModel(
    val id: String,
    val title: String,
    val amount: String,
    val dateFormated: String,
    val isExpense: Boolean,
    val categoryId: String
)

fun Transaction.toUiModel() =
    TransactionUiModel(
        id = id.toString(),
        title = title,
        amount = "${amount.formatAmount()} ${currency.code}",
        isExpense = type == TransactionType.EXPENSE,
        dateFormated = date.formatDate(),
        categoryId = category.id.toString()
    )

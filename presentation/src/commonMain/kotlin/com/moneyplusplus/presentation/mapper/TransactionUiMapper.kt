package com.moneyplusplus.presentation.mapper

import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.entity.TransactionType
import com.moneyplusplus.presentation.formater.CurrencyFormatter
import com.moneyplusplus.presentation.formater.DateFormatter
import com.moneyplusplus.presentation.model.TransactionUiModel

fun Transaction.toUiModel() =
    TransactionUiModel(
        id = id.toString(),
        title = title,
        amount = "${CurrencyFormatter.format(amount)} ${currency.code}",
        isExpense = type == TransactionType.EXPENSE,
        dateFormated = DateFormatter.formatTransactionDate(date),
        categoryId = category.id.toString()
    )
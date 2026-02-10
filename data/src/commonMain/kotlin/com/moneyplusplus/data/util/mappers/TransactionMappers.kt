package com.moneyplusplus.data.util.mappers

import com.moneyplusplus.data.dto.AddTransactionRequest
import com.moneyplusplus.data.dto.TransactionType
import com.moneyplusplus.domain.entity.Income

fun Income.toAddTransactionRequest() = AddTransactionRequest(
    type = TransactionType.INCOME,
    amount = amount,
    date = date.toString(),
    note = note
)
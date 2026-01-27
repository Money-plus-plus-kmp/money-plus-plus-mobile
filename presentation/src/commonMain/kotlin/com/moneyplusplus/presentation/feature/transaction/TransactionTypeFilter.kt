package com.moneyplusplus.presentation.feature.transaction

import com.moneyplusplus.domain.entity.TransactionType
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.all
import money.presentation.generated.resources.expenses
import money.presentation.generated.resources.incomes
import org.jetbrains.compose.resources.StringResource

enum class TransactionTypeFilter(val labelResource: StringResource) {
    ALL(Res.string.all),
    INCOMES(Res.string.incomes),
    EXPENSES(Res.string.expenses)
}

fun TransactionTypeFilter.toDomainTransactionType() = when (this) {
    TransactionTypeFilter.ALL -> null
    TransactionTypeFilter.INCOMES -> TransactionType.INCOME
    TransactionTypeFilter.EXPENSES -> TransactionType.EXPENSE
}

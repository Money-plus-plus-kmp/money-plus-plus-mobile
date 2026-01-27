package com.moneyplusplus.domain.repository

import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.model.TransactionFilter

interface TransactionRepository {
    suspend fun getTransactions(
        transactionFilter: TransactionFilter
    ): Result<List<Transaction>>
}
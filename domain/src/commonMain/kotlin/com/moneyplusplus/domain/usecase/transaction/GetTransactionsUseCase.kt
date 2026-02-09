package com.moneyplusplus.domain.usecase.transaction

import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.repository.TransactionRepository

class GetTransactionsUseCase(
    private val transactionRepository: TransactionRepository

) {
    suspend operator fun invoke(transactionFilter: TransactionFilter): List<Transaction> =
        transactionRepository.getTransactions(transactionFilter).sortedByDescending { it.date }
}

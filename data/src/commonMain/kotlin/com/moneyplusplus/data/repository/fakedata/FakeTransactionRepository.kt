package com.moneyplusplus.data.repository

import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.repository.TransactionRepository
import kotlinx.coroutines.delay
import kotlin.uuid.ExperimentalUuidApi

class FakeTransactionRepository : TransactionRepository {

    private val allTransactions: List<Transaction> = FakeDataSource.getFakeTransactions(100)

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun getTransactions(
        transactionFilter: TransactionFilter
    ): List<Transaction> {

        delay(1000)

        var filteredList = allTransactions

        if (transactionFilter.categoriesIds.isNotEmpty()) {
            filteredList = filteredList.filter { transaction ->
                transactionFilter.categoriesIds.contains(transaction.category.id)
            }
        }

        transactionFilter.date.let { filterDate ->
            filteredList = filteredList.filter { transaction ->
                transaction.date.year == filterDate.year &&
                        transaction.date.month == filterDate.month
            }
        }
        return filteredList.ifEmpty {
            emptyList()
        }
    }
}
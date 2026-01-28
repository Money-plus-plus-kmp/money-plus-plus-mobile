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
    ): Result<List<Transaction>> {

        delay(1000)

        var filteredList = allTransactions

        transactionFilter.type?.let { type ->
            filteredList = filteredList.filter { it.type == type }
        }
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
        return if (filteredList.isNotEmpty()) {
            Result.success(filteredList)
        } else {
            Result.success(emptyList())
        }
    }
}
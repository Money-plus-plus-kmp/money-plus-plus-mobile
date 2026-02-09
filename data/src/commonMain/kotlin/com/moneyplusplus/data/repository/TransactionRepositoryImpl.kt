package com.moneyplusplus.data.repository

import com.moneyplusplus.data.dto.TransactionResponse
import com.moneyplusplus.data.mapper.toDomain
import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.model.TransactionFilter
import com.moneyplusplus.domain.repository.TransactionRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class TransactionRepositoryImpl(
    private val client: HttpClient
) : TransactionRepository {
    override suspend fun getTransactions(
        transactionFilter: TransactionFilter
    ): List<Transaction> = callTransactions(transactionFilter)


    private suspend fun callTransactions(transactionFilter: TransactionFilter): List<Transaction> {
        val response = client.get("transactions") {

            parameter("date", transactionFilter.date.toString())

            transactionFilter.categoriesIds.forEach { categoryId ->
                parameter("category_ids", categoryId.toString())
            }
        }.body<List<TransactionResponse>>()
        return response.map { it.toDomain() }
    }

}

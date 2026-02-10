package com.moneyplusplus.data.repository

import com.moneyplusplus.data.util.mappers.toAddTransactionRequest
import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.domain.repository.IncomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.*

class IncomeRepositoryImpl(
    private val client: HttpClient
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        client.post("/transaction/add-transaction") {
            contentType(ContentType.Application.Json)
            setBody(income.toAddTransactionRequest())
        }
    }
}
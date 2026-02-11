package com.moneyplusplus.data.repository

import com.moneyplusplus.data.util.mappers.toAddTransactionRequest
import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.domain.repository.IncomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class IncomeRepositoryImpl(
    private val client: HttpClient
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        client.post("api/v1/transaction/add-transaction") {
            contentType(ContentType.Application.Json)
            setBody(income.toAddTransactionRequest())
        }
    }
}
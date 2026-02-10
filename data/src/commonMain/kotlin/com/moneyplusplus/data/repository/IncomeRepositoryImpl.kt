package com.moneyplusplus.data.repository

import com.moneyplusplus.data.util.mappers.toAddTransactionRequest
import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.domain.repository.IncomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*

class IncomeRepositoryImpl(
    private val client: HttpClient
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        println("Trace + repo $income")
        val body = income.toAddTransactionRequest()

        println("Trace + $body")
        val response = client.post("api/v1/transaction/add-transaction") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }

        println("Trace + response = $response")

        println("Trace + body = ${response.bodyAsText()}")
    }
}
package com.moneyplusplus.data.repository

import com.moneyplusplus.domain.entity.Income
import com.moneyplusplus.domain.repository.IncomeRepository
import io.ktor.client.HttpClient

class IncomeRepositoryImpl(
    private val client: HttpClient
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        /*
            client.post("/api/income") {
                contentType(ContentType.Application.Json)
                setBody(income)
            }
        */
        TODO("Not yet implemented")
    }
}
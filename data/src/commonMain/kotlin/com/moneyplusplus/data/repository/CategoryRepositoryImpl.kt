package com.moneyplusplus.data.repository

import com.moneyplusplus.data.dto.CategoryResponse
import com.moneyplusplus.data.mapper.toDomain
import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.domain.repository.CategoryRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CategoryRepositoryImpl(
    private val client: HttpClient
) : CategoryRepository {
    override suspend fun getSuggestedCategories(query: String): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCategories(
        userId: String,
        categories: List<Category>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<Category> {
        val response = client.get("categories").body<List<CategoryResponse>>()
        return response.map { it.toDomain() }
    }
}
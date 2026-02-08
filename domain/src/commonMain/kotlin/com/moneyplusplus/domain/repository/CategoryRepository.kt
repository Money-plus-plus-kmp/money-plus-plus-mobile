package com.moneyplusplus.domain.repository
import com.moneyplusplus.domain.entity.Category

interface CategoryRepository {
    suspend fun getSuggestedCategories(query: String): List<Category>
    suspend fun saveCategories(userId: String, categories: List<Category>)
    suspend fun getCategories(): List<Category>
}
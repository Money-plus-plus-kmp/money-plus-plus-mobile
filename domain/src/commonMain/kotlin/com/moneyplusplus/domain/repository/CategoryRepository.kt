package com.moneyplusplus.domain.repository
import com.moneyplusplus.domain.entity.Category

interface CategoryRepository {
    suspend fun getSuggestedCategories(): List<Category>
    suspend fun saveCategories(userId: String, categories: List<Category>)
}

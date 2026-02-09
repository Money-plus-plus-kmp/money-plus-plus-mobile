package com.moneyplusplus.data.repository.fakedata

import com.moneyplusplus.data.repository.FakeDataSource
import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.domain.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class FakeCategoryRepository
    : CategoryRepository {
    private val allCategories: List<Category> = FakeDataSource.getFakeCategories()


    override suspend fun getCategories(): List<Category> {
        delay(1500)

        return allCategories

    }

    override suspend fun getSuggestedCategories(query: String): List<Category> {
        TODO("Provide the return value")
    }

    override suspend fun saveCategories(userId: String, categories: List<Category>) {
        delay(1000)
    }
}
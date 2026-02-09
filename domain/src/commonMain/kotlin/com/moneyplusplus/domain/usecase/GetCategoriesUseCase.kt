package com.moneyplusplus.domain.usecase

import com.moneyplusplus.domain.repository.CategoryRepository

class GetCategoriesUseCase(private val repository: CategoryRepository) {
    suspend operator fun invoke() = repository.getCategories()
}
package com.moneyplusplus.presentation.model

import com.moneyplusplus.domain.entity.Category

data class CategoryUiModel(
    val id: String,
    val name: String,
)

fun Category.toUiModel() = CategoryUiModel(
    id = id.toString(),
    name = name
)

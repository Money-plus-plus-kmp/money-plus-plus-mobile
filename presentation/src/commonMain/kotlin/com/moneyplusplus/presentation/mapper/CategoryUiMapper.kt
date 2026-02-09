package com.moneyplusplus.presentation.mapper

import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.presentation.model.CategoryUiModel


fun Category.toUiModel() = CategoryUiModel(
    id = id.toString(),
    name = name
)
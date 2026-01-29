package com.moneyplusplus.data.mapper

import com.moneyplusplus.data.dto.CategoryResponse
import com.moneyplusplus.domain.entity.Category
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun CategoryResponse.toDomain(): Category = Category(
    id = Uuid.parse(id),
    name = name,
)
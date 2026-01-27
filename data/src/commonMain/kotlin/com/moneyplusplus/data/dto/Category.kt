package com.moneyplusplus.data.dto

import com.moneyplusplus.domain.entity.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)


@OptIn(ExperimentalUuidApi::class)
fun CategoryResponse.toDomain(): Category = Category(
    id = Uuid.parse(id),
    name = name,
)
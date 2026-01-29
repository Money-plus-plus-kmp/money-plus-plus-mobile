package com.moneyplusplus.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)

package com.moneyplusplus.domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)
data class Category(
    val id: Uuid,
    val name: String
)

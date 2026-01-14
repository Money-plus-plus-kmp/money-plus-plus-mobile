package com.moneyplusplus.domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)
data class User(
    val id: Uuid,
    val email: String,
    val name: String
)
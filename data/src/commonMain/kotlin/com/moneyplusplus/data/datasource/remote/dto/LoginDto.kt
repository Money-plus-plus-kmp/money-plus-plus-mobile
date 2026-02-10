package com.moneyplusplus.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)
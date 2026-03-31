package com.moneyplusplus.data.datasource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val code: Int,
    val message: String,
    val data: LoginData
)

@Serializable
data class LoginData(
    val token: String,
    val refreshToken: String
)
package com.moneyplusplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordRequest(
    val email: String
)

@Serializable
data class ForgetPasswordResponse(
    val message: String
)

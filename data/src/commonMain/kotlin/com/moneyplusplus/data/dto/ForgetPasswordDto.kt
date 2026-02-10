package com.moneyplusplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordDto(
    val email: String
)


package com.moneyplusplus.data.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordDto(
    val email: String
)


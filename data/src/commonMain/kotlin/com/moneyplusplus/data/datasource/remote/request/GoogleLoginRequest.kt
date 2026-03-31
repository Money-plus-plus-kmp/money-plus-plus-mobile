package com.moneyplusplus.data.datasource.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class GoogleLoginRequest(
    val token: String
)
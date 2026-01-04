package com.moneyplusplus.domain.repository

import com.moneyplusplus.domain.entity.User

interface AuthRepository {

    suspend fun createAccount(
        email: String,
        name: String,
        password: String
    ): User

    suspend fun login(
        email: String,
        password: String
    ): User

    suspend fun resetPassword(
        email: String
    )

    suspend fun logout()

}

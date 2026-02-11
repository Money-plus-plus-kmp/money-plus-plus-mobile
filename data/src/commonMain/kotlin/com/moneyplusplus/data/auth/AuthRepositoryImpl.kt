package com.moneyplusplus.data.auth

import com.moneyplusplus.domain.entity.User
import com.moneyplusplus.domain.repository.AuthRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AuthRepositoryImpl() : AuthRepository {

    override suspend fun createAccount(email: String, name: String, password: String): User {
        return User(
            id = Uuid.random(),
            email = email.trim(),
            name = name.trim()
        )
    }

    override suspend fun login(email: String, password: String): User {
        // temp access token until login
        AuthTokenProvider.accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OThiNjM1MDA2ZGUwOTYwMTAxYjBmMWQiLCJpYXQiOjE3NzA3NTAwMTEsImV4cCI6MTc3MDc1NzIxMX0.9udCT_EzDXt2sJgNNgzU1njdigPz3OFBneBCmB9f6Y8"

        return User(
            id = Uuid.random(),
            email = "",
            name = ""
        )
    }

    override suspend fun signInWithGoogle(idToken: String): User {
        return User(
            id = Uuid.random(),
            email = "",
            name = ""
        )
    }

    override suspend fun resetPassword(email: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {}
}

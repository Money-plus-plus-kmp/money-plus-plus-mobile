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

    override suspend fun resetPassword(email: String) {}

    override suspend fun logout() {}
}



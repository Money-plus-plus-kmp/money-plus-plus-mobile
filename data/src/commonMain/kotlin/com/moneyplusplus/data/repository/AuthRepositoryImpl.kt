package com.moneyplusplus.data.repository

import com.moneyplusplus.data.datasource.remote.auth.AuthDataSource
import com.moneyplusplus.data.datasource.remote.auth.google.GoogleAuthDataSource
import com.moneyplusplus.domain.exception.AuthenticationException
import com.moneyplusplus.domain.repository.AuthRepository
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val googleAuthDataSource: GoogleAuthDataSource
) : AuthRepository {
    override suspend fun createAccount(email: String, name: String, password: String) {
        authDataSource.createAccount(email, name, password)

    }

    override suspend fun login(email: String, password: String) {
        authDataSource.login(email, password)
        //TODO save token

    }

    override suspend fun signInWithGoogle() {
        val googleToken = googleAuthDataSource.signInWithGoogle()
            ?: throw AuthenticationException.InvalidCredentials
        authDataSource.signInWithGoogle(googleToken)
        //TODO save auth token

    }

    override suspend fun resetPassword(email: String) {
        authDataSource.resetPassword(email)

    }

    override suspend fun logout() {
        authDataSource.logout()
    }

}
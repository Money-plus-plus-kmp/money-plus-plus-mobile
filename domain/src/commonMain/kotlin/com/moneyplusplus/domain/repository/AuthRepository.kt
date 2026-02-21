package com.moneyplusplus.domain.repository

interface AuthRepository {

    suspend fun createAccount(email: String, name: String, password: String)
    suspend fun login(email: String, password: String)
    suspend fun signInWithGoogle()
    suspend fun resetPassword(email: String)
    suspend fun logout()

}
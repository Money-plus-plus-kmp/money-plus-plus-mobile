package com.moneyplusplus.data.datasource.remote.auth

interface GoogleAuthDataSource {
    suspend fun signInWithGoogle(): String?
}
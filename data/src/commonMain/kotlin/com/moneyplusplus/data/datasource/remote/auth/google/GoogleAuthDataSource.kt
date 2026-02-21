package com.moneyplusplus.data.datasource.remote.auth.google

interface GoogleAuthDataSource {
        suspend fun signInWithGoogle(): String?
}
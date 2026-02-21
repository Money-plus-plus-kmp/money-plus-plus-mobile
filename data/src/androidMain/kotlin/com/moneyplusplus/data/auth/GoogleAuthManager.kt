package com.moneyplusplus.data.auth

import android.annotation.SuppressLint
import android.content.Context
import android.credentials.GetCredentialException
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.moneyplusplus.data.BuildConfig
import com.moneyplusplus.data.datasource.remote.auth.google.GoogleAuthDataSource

class GoogleAuthManager(private val context: Context) : GoogleAuthDataSource {
    private val clientId = BuildConfig.GOOGLE_CLIENT_ID

    private val credentialManager = CredentialManager.Companion.create(context)

    override suspend fun signInWithGoogle(): String? {
        return try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(clientId)
                .setAutoSelectEnabled(true)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(
                request = request,
                context = context
            )

            val credential = result.credential

            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleCredential = GoogleIdTokenCredential.Companion.createFrom(credential.data)
                googleCredential.idToken
            } else {
                null
            }
        } catch (@SuppressLint("NewApi") e: GetCredentialException) {
            e.printStackTrace()
            null
        }
    }
}
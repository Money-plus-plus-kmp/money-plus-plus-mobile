package com.moneyplusplus.data.repository

import com.moneyplusplus.data.dto.ForgetPasswordRequest
import com.moneyplusplus.data.dto.ForgetPasswordResponse
import com.moneyplusplus.domain.entity.User
import com.moneyplusplus.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {
    override suspend fun createAccount(
        email: String,
        name: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithGoogle(idToken: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String): String {
        val response: ForgetPasswordResponse =
            client.get("//fake//") {
            contentType(ContentType.Application.Json)
            setBody(ForgetPasswordRequest(email))
        }.body()

        return response.message
    }


    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}
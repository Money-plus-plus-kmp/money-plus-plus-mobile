package com.moneyplusplus.data.datasource.remote.auth

import com.moneyplusplus.data.base.ApiEndpoints
import com.moneyplusplus.data.datasource.remote.request.ForgotPasswordRequest
import com.moneyplusplus.data.datasource.remote.request.GoogleLoginRequest
import com.moneyplusplus.data.datasource.remote.request.LoginRequest
import com.moneyplusplus.data.datasource.remote.response.ForgetPasswordResponse
import com.moneyplusplus.data.datasource.remote.response.LoginResponse
import com.moneyplusplus.domain.entity.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AuthDataSource(
    private val client: HttpClient
) {

    suspend fun createAccount(email: String, name: String, password: String): User {
        return User(
            id = Uuid.random(),
            email = email.trim(),
            name = name.trim()
        )
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return client.post(urlString = ApiEndpoints.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        }.body()
    }

    suspend fun signInWithGoogle(token: String): LoginResponse {
        return client.post(urlString = ApiEndpoints.TOKEN_VALIDATION) {
            contentType(ContentType.Application.Json)
            setBody(GoogleLoginRequest(token))
        }.body()
    }

    suspend fun resetPassword(email: String): String {
        val response: ForgetPasswordResponse =
            client.get(urlString = ApiEndpoints.FORGOT_PASSWORD) {
                contentType(ContentType.Application.Json)
                setBody(ForgotPasswordRequest(email))
            }.body()

        return response.message
    }

    suspend fun logout() {
        TODO("Not yet implemented")
    }
}
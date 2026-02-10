package com.moneyplusplus.data.util.network

import com.moneyplusplus.data.auth.AuthTokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    engine: HttpClientEngine,
    baseUrl: String
): HttpClient =
    HttpClient(engine) {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }

        defaultRequest {
            url(baseUrl)
            contentType(Json)
            AuthTokenProvider.accessToken?.let { token ->
                header(HttpHeaders.Authorization, "Bearer $token")
            }
        }
    }

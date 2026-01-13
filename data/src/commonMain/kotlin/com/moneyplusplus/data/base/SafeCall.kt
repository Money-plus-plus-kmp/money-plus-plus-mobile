package com.moneyplusplus.data.base

import kotlinx.coroutines.CancellationException

/**
 * Safely executes a suspending block and wraps the result in Kotlin's Result type.
 * Catches all exceptions except CancellationException (which should propagate).
 *
 * Usage:
 * ```
 * suspend fun getUser(id: String): Result<User> = safeCall {
 *     apiService.getUser(id)
 * }
 * ```
 *
 * @param block The suspending function to execute
 * @return Result.success with the data, or Result.failure with the exception
 */
suspend fun <T> safeCall(block: suspend () -> T): Result<T> =
    runCatching { block() }
        .onFailure { if (it is CancellationException) throw it }


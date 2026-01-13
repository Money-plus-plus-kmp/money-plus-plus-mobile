package com.moneyplusplus.data.base

/**
 * Extension functions for common data layer operations using Kotlin's built-in Result
 */

/**
 * Maps the success value of a Result
 */
inline fun <T, R> Result<T>.mapResult(transform: (T) -> R): Result<R> {
    return map(transform)
}
package com.moneyplusplus.domain.usecase

import com.moneyplusplus.domain.exception.AppException

class EmailValidator {

    private val emailRegex =
        Regex(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            RegexOption.IGNORE_CASE
        )

    operator fun invoke(email: String) {
        when {
            email.isBlank() ->
                throw AppException.AuthenticationException.EmptyEmail()

            !emailRegex.matches(email) ->
                throw AppException.AuthenticationException.InvalidEmail()

        }
    }
}
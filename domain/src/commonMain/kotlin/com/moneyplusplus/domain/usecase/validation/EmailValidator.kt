package com.moneyplusplus.domain.usecase.validation

import com.moneyplusplus.domain.exception.AppException.ValidationException.Email.InvalidEmail
import com.moneyplusplus.domain.exception.AppException.ValidationException.Email.Empty

class EmailValidator {

    private val emailRegex =
        Regex(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            RegexOption.IGNORE_CASE
        )

    operator fun invoke(email: String) {
        when {
            email.isBlank() ->
                throw Empty

            !emailRegex.matches(email) ->
                throw InvalidEmail


        }
    }
}
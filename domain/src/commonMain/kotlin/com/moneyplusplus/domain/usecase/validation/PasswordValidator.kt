package com.moneyplusplus.domain.usecase.validation

import com.moneyplusplus.domain.exception.AppException
import com.moneyplusplus.domain.exception.ValidationException.Password.Empty
import com.moneyplusplus.domain.exception.ValidationException.Password.InvalidPassword


class PasswordValidator {

    operator fun invoke(password: String) {

        when {
            password.isBlank() ->
                throw Empty

            password.length < MIN_LENGTH ->
                throw InvalidPassword

            !password.any { it.isDigit() } || !password.any { it.isLetter() } ->
                throw InvalidPassword
        }
    }

    private companion object {
         const val MIN_LENGTH = 8
    }
}
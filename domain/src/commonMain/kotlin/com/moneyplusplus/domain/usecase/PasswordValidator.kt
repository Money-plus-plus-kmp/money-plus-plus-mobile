package com.moneyplusplus.domain.usecase

import com.moneyplusplus.domain.exception.AppException

class PasswordValidator {

    operator fun invoke(password: String) {
        val hasDigitalNumbers = password.any { it.isDigit() }
        val hasLetters = password.any { it.isLetter() }

        when {
            password.isBlank() ->
                throw AppException.AuthenticationException.EmptyPassword()

            password.length < 8 ->
                throw AppException.AuthenticationException.InvalidPassword()

            !hasDigitalNumbers || !hasLetters ->
                throw AppException.AuthenticationException.InvalidPassword()
        }
    }
}
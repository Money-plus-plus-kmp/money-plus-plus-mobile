package com.moneyplusplus.domain.usecase.validation

import com.moneyplusplus.domain.exception.AppException.ValidationException.Password.Empty
import com.moneyplusplus.domain.exception.AppException.ValidationException.Password.InvalidPassword


class PasswordValidator {

    operator fun invoke(password: String) {
        val hasDigitalNumbers = password.any { it.isDigit() }
        val hasLetters = password.any { it.isLetter() }

        when {
            password.isBlank() ->
                throw Empty

            password.length < 8 ->
                throw InvalidPassword

            !hasDigitalNumbers || !hasLetters ->
                throw InvalidPassword
        }
    }
}
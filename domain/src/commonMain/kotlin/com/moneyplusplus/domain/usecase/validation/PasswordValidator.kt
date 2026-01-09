package com.moneyplusplus.domain.usecase.validation

import com.moneyplusplus.domain.exception.ValidationException.Password.Empty
import com.moneyplusplus.domain.exception.ValidationException.Password.TooShort


class PasswordValidator {

    operator fun invoke(password: String) {
        when {
            password.isBlank() ->
                throw Empty()

            password.length < 8 ->
                throw TooShort()
        }
    }
}
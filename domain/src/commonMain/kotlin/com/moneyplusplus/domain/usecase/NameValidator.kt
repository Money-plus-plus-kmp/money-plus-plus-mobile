package com.moneyplusplus.domain.usecase

import com.moneyplusplus.domain.exception.AppException

class NameValidator {

    operator fun invoke(name: String) {
        when {
            name.isBlank() ->
                throw AppException.AuthenticationException.EmptyName()

            name.length < 3 ||
                    !name.all { it.isLetter() || it.isWhitespace() } ->
                throw AppException.AuthenticationException.InvalidUserName()
        }
    }
}


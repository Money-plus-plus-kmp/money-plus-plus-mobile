package com.moneyplusplus.domain.usecase.validation

import com.moneyplusplus.domain.exception.ValidationException.Name.Empty

class NameValidator {

    operator fun invoke(name: String) {
        when {
            name.isBlank() ->
                throw Empty
        }
    }
}
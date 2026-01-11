package com.moneyplusplus.domain.exception


sealed class AppException : Exception()

sealed class AuthenticationException : AppException() {
    data object EmailAlreadyExists : AuthenticationException()
    data object InvalidCredentials : AuthenticationException()
}

sealed class ValidationException : AppException() {

    sealed class Email : ValidationException() {
        data object Empty : Email()
        data object InvalidEmail : Email()
    }

    sealed class Password : ValidationException() {
        data object Empty : Password()
        data object TooShort : Password()
    }

    sealed class Name : ValidationException() {
        data object Empty : Name()
    }
}
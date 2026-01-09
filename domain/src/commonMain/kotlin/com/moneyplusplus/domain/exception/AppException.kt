package com.moneyplusplus.domain.exception


sealed class AppException : Exception()

sealed class AuthenticationException : AppException() {
    class EmailAlreadyExists : AuthenticationException()
    class InvalidCredentials : AuthenticationException()
}

sealed class ValidationException : AppException() {

    sealed class Email : ValidationException() {
        class Empty : Email()
        class InvalidFormat : Email()
    }

    sealed class Password : ValidationException() {
        class Empty : Password()
        class TooShort : Password()
    }

    sealed class Name : ValidationException() {
        class Empty : Name()
        class TooShort : Name()
    }
}
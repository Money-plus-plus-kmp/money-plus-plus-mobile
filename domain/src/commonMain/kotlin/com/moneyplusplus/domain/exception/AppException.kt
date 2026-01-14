package com.moneyplusplus.domain.exception


sealed class AppException : Exception()

sealed class AuthenticationException : AppException() {
    data object EmailAlreadyExists : AuthenticationException()
    data object InvalidCredentials : AuthenticationException()
}

sealed class ValidationException : AppException() {

    sealed class Email : ValidationException() {
        object Empty : Email()
        object InvalidEmail : Email()
    }

    sealed class Password : ValidationException() {
        object Empty : Password()
        object InvalidPassword : Password()
    }

    sealed class Name : ValidationException() {
        object Empty : Name()
    }
}
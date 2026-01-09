package com.moneyplusplus.domain.exception


sealed class AppException : Exception() {
    sealed class ValidationException : AppException() {

        sealed class Email : ValidationException() {
            data object Empty : Email()
            data object InvalidEmail : Email()
        }

        sealed class Password : ValidationException() {
            data object Empty : Password()
            data object InvalidPassword : Password()
        }

        sealed class Name : ValidationException() {
           data object Empty : Name()
        }
    }

    sealed class AuthException : AppException() {
        data object EmailAlreadyExists : AuthException()
        data object InvalidCredentials : AuthException()
    }
}
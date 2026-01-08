package com.moneyplusplus.domain.exception


sealed class AppException : Exception() {
    class UnknownException() : AppException()

    sealed class NetworkException : AppException() {
        class UnAuthorizedException() : NetworkException()
        class NoInternetException() : NetworkException()
        class UnKnownNetworkException() : NetworkException()
    }

    sealed class AuthenticationException() : AppException() {
        class EmptyName : AuthenticationException()
        class InvalidUserName : AuthenticationException()


        class EmptyEmail : AuthenticationException()
        class InvalidEmail : AuthenticationException()

        class EmptyPassword : AuthenticationException()
        class InvalidPassword : AuthenticationException()

        class InvalidUserCredential : AuthenticationException()
    }
}
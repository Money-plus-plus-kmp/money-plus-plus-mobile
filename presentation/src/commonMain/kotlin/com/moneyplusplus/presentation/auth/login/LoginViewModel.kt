package com.moneyplusplus.presentation.auth.login

import com.moneyplusplus.domain.entity.User
import com.moneyplusplus.domain.exception.AuthenticationException
import com.moneyplusplus.domain.exception.ValidationException
import com.moneyplusplus.domain.repository.AuthRepository
import com.moneyplusplus.presentation.base.BaseViewModel
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.error_email_empty
import money.presentation.generated.resources.error_email_invalid
import money.presentation.generated.resources.error_generic
import money.presentation.generated.resources.error_invalid_credentials
import money.presentation.generated.resources.error_password_empty
import money.presentation.generated.resources.error_password_invalid
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class LoginViewModel(
    @Provided val authRepository: AuthRepository
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> onEmailChanged(intent.newEmail)
            is LoginIntent.PasswordChanged -> onPasswordChanged(intent.newPassword)
            LoginIntent.LoginClicked -> onLoginClicked()
            LoginIntent.ForgetPasswordClicked -> forgetPassword()
            LoginIntent.TogglePasswordVisibility -> togglePasswordVisibility()
            LoginIntent.ContinueWithGoogleClicked -> continueWithGoogle()
            LoginIntent.CreateNewAccountClicked -> createNewAccount()

        }
    }

    private fun onPasswordChanged(newPassword: String) {
        updateState { copy(password = newPassword, emailError = null, passwordError = null) }

    }

    private fun onEmailChanged(newEmail: String) {
        updateState { copy(email = newEmail, emailError = null, passwordError = null) }
    }

    private fun onLoginClicked() {
        tryExecute(
            onStart = ::setLoadingState,
            block = ::loginUser,
            onSuccess = ::handleLoginSuccess,
            onError = ::handleLoginError
        )
    }

    private suspend fun loginUser(): User {
        val email = currentState.email
        val password = currentState.password
        return authRepository.login(email, password)
    }

    private fun handleLoginSuccess(user: User) {
        updateState { copy(
            isLoading = false,
            email = "",
            password = "",
            emailError = null,
            passwordError = null
        ) }
        sendEffect(LoginEffect.NavigateToHome)
    }

    private fun handleLoginError(error: Throwable) {
        updateState { copy(isLoading = false) }
        when (error) {
            is ValidationException -> handleValidationError(error)
            is AuthenticationException -> handleAuthenticationError(error)
            else -> {
                updateState {
                    copy(
                        emailError = Res.string.error_generic,
                        passwordError = Res.string.error_generic
                    )
                }
            }
        }
    }

    private fun handleValidationError(error: ValidationException) {
        when (error) {

            is ValidationException.Email.Empty -> updateState {
                copy(emailError = Res.string.error_email_empty)
            }

            is ValidationException.Email.InvalidEmail -> updateState {
                copy(emailError = Res.string.error_email_invalid)
            }

            is ValidationException.Password.Empty -> updateState {
                copy(passwordError = Res.string.error_password_empty)
            }

            is ValidationException.Password.InvalidPassword -> updateState {
                copy(passwordError = Res.string.error_password_invalid)
            }

            else -> Unit
        }
    }

    private fun handleAuthenticationError(error: AuthenticationException) {
        when (error) {
            AuthenticationException.InvalidCredentials -> updateState {
                copy(
                    emailError = Res.string.error_invalid_credentials,
                    passwordError = Res.string.error_invalid_credentials
                )
            }

            AuthenticationException.EmailAlreadyExists -> {
            }
        }
    }


    private fun togglePasswordVisibility() {
        updateState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun forgetPassword() {
        sendEffect(LoginEffect.NavigateToForgetPassword)
    }

    private fun continueWithGoogle() {
    }

    private fun createNewAccount() {
        sendEffect(LoginEffect.NavigateToCreateAccount)
    }

    private fun setLoadingState() {
        updateState { copy(isLoading = true) }
    }

}
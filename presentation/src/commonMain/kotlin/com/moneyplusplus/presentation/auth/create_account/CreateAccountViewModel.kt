package com.moneyplusplus.presentation.auth.create_account

import com.moneyplusplus.domain.exception.AuthenticationException
import com.moneyplusplus.domain.exception.ValidationException
import com.moneyplusplus.domain.usecase.validation.EmailValidator
import com.moneyplusplus.domain.usecase.validation.NameValidator
import com.moneyplusplus.domain.usecase.validation.PasswordValidator
import com.moneyplusplus.presentation.base.BaseViewModel
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.error_email_already_exists
import money.presentation.generated.resources.error_email_empty
import money.presentation.generated.resources.error_email_invalid
import money.presentation.generated.resources.error_invalid_credentials
import money.presentation.generated.resources.error_password_empty
import money.presentation.generated.resources.error_password_invalid
import money.presentation.generated.resources.error_username_empty

class CreateAccountViewModel(
    private val emailValidator: EmailValidator,
    private val nameValidator: NameValidator,
    private val passwordValidator: PasswordValidator
) : BaseViewModel<CreateAccountState, CreateAccountIntent, CreateAccountEffect>(
    initialState = CreateAccountState()
) {

    override fun handleIntent(intent: CreateAccountIntent) {
        when (intent) {
            is CreateAccountIntent.OnUsernameChanged -> {
                updateState { copy(username = intent.username) }
                validateForm()
            }

            is CreateAccountIntent.OnEmailChanged -> {
                updateState { copy(email = intent.email) }
                validateForm()
            }

            is CreateAccountIntent.OnPasswordChanged -> {
                updateState { copy(password = intent.password) }
                validateForm()
            }

            CreateAccountIntent.OnBackClicked -> sendEffect(CreateAccountEffect.NavigateBack)
            CreateAccountIntent.OnCreateAccountClicked -> createAccount()
        }
    }

    private fun validateForm() {
        val currentState = currentState
        var isValid = true

        try {
            emailValidator(currentState.email)
            nameValidator(currentState.username)
            passwordValidator(currentState.password)
        } catch (_: ValidationException) {
            isValid = false
        }

        updateState { copy(isFormValid = isValid) }
    }

    private fun createAccount() {
        tryExecute(
            onStart = ::setLoadingState,
            block = ::createNewAccount,
            onSuccess = { onCreateAccountSuccess() },
            onError = ::handleCreateAccountError
        )
    }

    private fun createNewAccount() {
        clearErrors()
        emailValidator(currentState.email)
        nameValidator(currentState.username)
        passwordValidator(currentState.password)

        // Replace with real use case later

    }

    private fun clearErrors() {
        updateState { copy(emailError = null, passwordError = null, userNameError = null) }
    }

    private fun setLoadingState() {
        updateState { copy(isLoading = true) }
    }

    private fun onCreateAccountSuccess() {
        updateState { copy(isLoading = false) }
        sendEffect(CreateAccountEffect.NavigateToAccountSetup)
    }

    private fun handleCreateAccountError(error: Throwable) {
        updateState { copy(isLoading = false) }

        when (error) {
            is ValidationException -> handleValidationError(error)
            is AuthenticationException -> handleAuthenticationError(error)
        }
    }

    private fun handleAuthenticationError(error: AuthenticationException) {
        when (error) {
            AuthenticationException.InvalidCredentials -> updateState {
                copy(
                    userNameError = Res.string.error_invalid_credentials,
                    emailError = Res.string.error_invalid_credentials,
                    passwordError = Res.string.error_invalid_credentials
                )
            }

            AuthenticationException.EmailAlreadyExists -> updateState {
                copy(emailError = Res.string.error_email_already_exists)
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

            is ValidationException.Name.Empty -> updateState {
                copy(userNameError = Res.string.error_username_empty)
            }

            is ValidationException.Password.Empty -> updateState {
                copy(passwordError = Res.string.error_password_empty)
            }

            is ValidationException.Password.InvalidPassword -> updateState {
                copy(passwordError = Res.string.error_password_invalid)
            }
        }
    }

}

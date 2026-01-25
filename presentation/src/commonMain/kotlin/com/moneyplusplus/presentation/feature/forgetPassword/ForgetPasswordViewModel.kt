package com.moneyplusplus.presentation.feature.forgetPassword

import com.moneyplusplus.domain.usecase.validation.EmailValidator
import com.moneyplusplus.presentation.base.BaseViewModel

class ForgetPasswordViewModel(
    private val emailValidator: EmailValidator,
) : BaseViewModel<
        ForgetPasswordUiState,
        ForgetPasswordIntent,
        ForgetPasswordEffect
        >(ForgetPasswordUiState()) {

    override fun handleIntent(intent: ForgetPasswordIntent) {
        when (intent) {
            is ForgetPasswordIntent.OnBackClick -> sendEffect(ForgetPasswordEffect.NavigateBack)
            is ForgetPasswordIntent.OnEmailChanged -> updateState { copy(email = intent.email) }
            is ForgetPasswordIntent.OnSendResetLinkClick -> {
                tryExecute(
                    block = ::validateEmail,
                    onSuccess = { emailValidationSuccess() },
                    onError = ::emailValidationError
                )
            }
        }
    }

    private fun validateEmail() {
        emailValidator(email = currentState.email)
    }

    private fun emailValidationSuccess() {
        updateState {
            copy(isEnableButton = true)
        }
        sendEffect(ForgetPasswordEffect.NavigateToLogin)
    }

    private fun emailValidationError(errorMessage: Throwable) {
        updateState {
            copy(isEnableButton = false)
        }
        sendEffect(
            ForgetPasswordEffect.ShowSnackbar(
                message = errorMessage.message.toString()
            )
        )
    }
}

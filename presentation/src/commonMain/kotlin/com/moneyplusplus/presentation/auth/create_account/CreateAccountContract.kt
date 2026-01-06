package com.moneyplusplus.presentation.auth.create_account

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class CreateAccountState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isFormValid: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
) : UiState

sealed interface CreateAccountIntent : UiIntent {
    data class OnUsernameChanged(val username: String) : CreateAccountIntent
    data class OnEmailChanged(val email: String) : CreateAccountIntent
    data class OnPasswordChanged(val password: String) : CreateAccountIntent
    data object OnCreateAccountClicked : CreateAccountIntent
    data object OnBackClicked : CreateAccountIntent
}

sealed interface CreateAccountEffect : UiEffect {
    data class ShowErrorSnackBar(val message: String) : CreateAccountEffect
    data object NavigateToAccountSetup : CreateAccountEffect
    data object NavigateBack : CreateAccountEffect
}

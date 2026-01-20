package com.moneyplusplus.presentation.login

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState
import org.jetbrains.compose.resources.StringResource

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: StringResource? = null,
    val passwordError: StringResource? = null,
    val canSubmit: Boolean = false,
    val isPasswordVisible: Boolean = false,
    ) : UiState

sealed interface LoginIntent : UiIntent {
    data class EmailChanged(val newEmail: String) : LoginIntent
    data class PasswordChanged(val newPassword: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
    data object ForgetPasswordClicked : LoginIntent
    data object LoginClicked : LoginIntent
    data object ContinueWithGoogleClicked : LoginIntent
    data object CreateNewAccountClicked : LoginIntent
}

sealed interface LoginEffect : UiEffect {
    data object NavigateToHome : LoginEffect
}
package com.moneyplusplus.presentation.screens.login

data class LoginScreenUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val canSubmit: Boolean = false
)
package com.moneyplusplus.presentation.screens.login

interface LoginInteractionListener {
    fun onEmailChanged(newEmail: String)
    fun onPasswordChanged(newPassword: String)
    fun onTogglePasswordVisibility()
    fun onForgetPasswordClicked()
    fun onLoginClicked()
    fun onContinueWithGoogleClicked()
    fun onCreateNewAccountClicked()
}
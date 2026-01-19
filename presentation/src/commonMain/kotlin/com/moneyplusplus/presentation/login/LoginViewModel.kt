package com.moneyplusplus.presentation.login

import com.moneyplusplus.presentation.base.BaseViewModel

class LoginViewModel : BaseViewModel<LoginScreenUiState, LoginScreenEffect>(LoginScreenUiState()),
    LoginInteractionListener {
    override fun onEmailChanged(newEmail: String) {
    }

    override fun onPasswordChanged(newPassword: String) {
    }

    override fun onTogglePasswordVisibility() {
    }

    override fun onForgetPasswordClicked() {
    }

    override fun onLoginClicked() {
    }

    override fun onContinueWithGoogleClicked() {
    }

    override fun onCreateNewAccountClicked() {
    }
}
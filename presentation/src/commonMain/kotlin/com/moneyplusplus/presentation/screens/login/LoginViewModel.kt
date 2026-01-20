package com.moneyplusplus.presentation.screens.login

import com.moneyplusplus.presentation.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
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
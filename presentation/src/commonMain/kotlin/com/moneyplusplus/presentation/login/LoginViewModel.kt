package com.moneyplusplus.presentation.login

import com.moneyplusplus.presentation.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel : BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> updateState { copy(email = intent.newEmail) }
            is LoginIntent.PasswordChanged -> updateState { copy(password = intent.newPassword) }
            LoginIntent.LoginClicked -> login()
            LoginIntent.ForgetPasswordClicked -> forgetPassword()
            LoginIntent.TogglePasswordVisibility -> togglePasswordVisibility()
            LoginIntent.ContinueWithGoogleClicked -> continueWithGoogle()
            LoginIntent.CreateNewAccountClicked -> createNewAccount()

        }
    }

    private fun login(){
        tryExecute(
            onStart = {},
            block = {},
            onSuccess = {},
            onError = {}
        )

    }
    private fun forgetPassword(){

    }
    private fun togglePasswordVisibility(){

    }
    private fun continueWithGoogle(){

    }
    private fun createNewAccount(){

    }


}
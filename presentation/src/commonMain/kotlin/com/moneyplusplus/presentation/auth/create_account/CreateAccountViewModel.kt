package com.moneyplusplus.presentation.auth.create_account

import com.moneyplusplus.presentation.base.BaseViewModel

class CreateAccountViewModel :
    BaseViewModel<CreateAccountState, CreateAccountIntent, CreateAccountEffect>(
        initialState = CreateAccountState()
    ) {

    override fun handleIntent(intent: CreateAccountIntent) {
        when (intent) {
            is CreateAccountIntent.OnUsernameChanged -> updateState { copy(username = intent.username) }
            is CreateAccountIntent.OnEmailChanged -> updateState { copy(email = intent.email) }
            is CreateAccountIntent.OnPasswordChanged -> updateState { copy(password = intent.password) }
            CreateAccountIntent.OnBackClicked -> sendEffect(CreateAccountEffect.NavigateBack)
            CreateAccountIntent.OnCreateAccountClicked -> createAccount()
        }
    }

    private fun createAccount() {
        tryExecute(
            block = ::createNewAccount,
            onStart = { updateState { copy(isLoading = true, error = null) } },
            onSuccess = { ::onCreateAccountSuccess },
            onError = { ::onCreateAccountError }
        )
    }

    private fun onCreateAccountSuccess() {
        updateState { copy(isLoading = false, error = null) }
        sendEffect(CreateAccountEffect.NavigateToAccountSetup)
    }

    private fun onCreateAccountError(throwable: Throwable) {
        val message = throwable.message.toString()
        updateState { copy(isLoading = false, error = message) }
        sendEffect(CreateAccountEffect.ShowErrorSnackBar(message))
    }

    private fun createNewAccount() {
        // Replace with real use case later
    }
}

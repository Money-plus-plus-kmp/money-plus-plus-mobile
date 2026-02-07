package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.BaseViewModel

class AccountSetupViewModel() :
    BaseViewModel<AccountSetupState, AccountSetupIntent, AccountSetupEffect>(
        initialState = AccountSetupState()
    ) {
    override fun handleIntent(intent: AccountSetupIntent) {
    }
}
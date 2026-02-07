package com.moneyplusplus.presentation.auth.create_account.creat_account_flow

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class AccountSetupState(
    val loading: Boolean = false,
    val isCurrencyBottomSheetVisible: Boolean = false,
    val salary: String = "",
): UiState
sealed interface AccountSetupIntent: UiIntent{}
sealed interface AccountSetupEffect: UiEffect{}
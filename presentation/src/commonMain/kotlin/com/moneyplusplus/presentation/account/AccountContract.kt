package com.moneyplusplus.presentation.account

import com.moneyplusplus.presentation.base.UiEffect
import com.moneyplusplus.presentation.base.UiIntent
import com.moneyplusplus.presentation.base.UiState

data class AccountState(
    val userProfile: UserUi? = null,
    val account: AccountUi? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState

sealed interface AccountIntent : UiIntent {
    data object EditProfile : AccountIntent
    data class NavigateToSettings(val type: SettingsType) : AccountIntent
}

sealed interface AccountEffect : UiEffect {
    data object NavigateToEditProfile : AccountEffect
    data class NavigateToSettings(val type: SettingsType) : AccountEffect
}

enum class SettingsType {
    MANAGE_CATEGORIES,
    APP_LANGUAGE,
    APP_THEME,
    CURRENCY,
    SALARY,
    FAQ,
    HELP_SUPPORT
}

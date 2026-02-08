package com.moneyplusplus.presentation.account

import com.moneyplusplus.domain.entity.Account
import com.moneyplusplus.domain.entity.User

data class AccountState(
    val userProfile: User? = null,
    val account: Account? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface AccountIntent {
    data object EditProfile : AccountIntent
    data class NavigateToSettings(val type: SettingsType) : AccountIntent
}

sealed interface AccountEffect {
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

package com.moneyplusplus.presentation.feature.forgetPassword

import com.moneyplusplus.presentation.base.UiEffect

sealed class ForgetPasswordEffect : UiEffect {
    data object NavigateBack : ForgetPasswordEffect()
    data object NavigateToLogin : ForgetPasswordEffect()
    data class ShowSnackbar(val message: String) : ForgetPasswordEffect()
}
package com.moneyplusplus.presentation.feature.forgetPassword

import com.moneyplusplus.presentation.base.UiIntent

sealed class ForgetPasswordIntent : UiIntent {
    object OnBackClick : ForgetPasswordIntent()

    data class OnEmailChanged(val email: String) : ForgetPasswordIntent()

    object OnSendResetLinkClick : ForgetPasswordIntent()

}
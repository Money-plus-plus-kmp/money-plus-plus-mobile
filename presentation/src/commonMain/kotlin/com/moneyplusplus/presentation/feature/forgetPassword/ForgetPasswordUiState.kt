package com.moneyplusplus.presentation.feature.forgetPassword

import com.moneyplusplus.presentation.base.UiState

data class ForgetPasswordUiState(
    val isEnableButton: Boolean = false,
    val email: String = ""
) : UiState

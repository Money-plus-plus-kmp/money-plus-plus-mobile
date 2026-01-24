package com.moneyplusplus.design_system.component.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class MSnackbarHostState {

    var currentSnackbarData by mutableStateOf<MSnackbarData?>(null)
        private set

    fun showSuccess(message: String) {
        currentSnackbarData = MSnackbarData(MSnackbarType.SUCCESS, message)
    }

    fun showError(message: String) {
        currentSnackbarData = MSnackbarData(MSnackbarType.ERROR, message)
    }

    fun dismiss() {
        currentSnackbarData = null
    }
}

@Composable
fun rememberMSnackbarHostState(): MSnackbarHostState {
    return remember { MSnackbarHostState() }
}
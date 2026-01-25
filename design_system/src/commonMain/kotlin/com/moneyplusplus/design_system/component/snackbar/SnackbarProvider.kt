package com.moneyplusplus.design_system.component.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

val LocalMSnackbarState = compositionLocalOf<MSnackbarState> {
    error("No MSnackbarState provided. Wrap your content with MSnackbarProvider.")
}


@Composable
fun MSnackbarProvider(
    content: @Composable () -> Unit
) {
    val snackbarState = remember { MSnackbarState() }

    CompositionLocalProvider(LocalMSnackbarState provides snackbarState) {
        Box(modifier = Modifier.fillMaxSize()) {

            content()

            MSnackbarHost(
                state = snackbarState,
            )
        }
    }
}
package com.moneyplusplus.design_system.component.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.animation.easingAnimation
import kotlinx.coroutines.delay

private val TOP_PADDING = 12.dp
private val HORIZONTAL_PADDING = 16.dp
private const val AUTO_DISMISS_DURATION = 3000L

@Composable
internal fun MSnackbarHost(
    state: MSnackbarState,
    modifier: Modifier = Modifier,
    autoDismissDuration: Long = AUTO_DISMISS_DURATION
) {
    val currentSnackbarData = state.currentSnackbarData
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            delay(autoDismissDuration)
            state.dismiss()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = currentSnackbarData != null,
            enter = slideInVertically(
                animationSpec = easingAnimation(),
                initialOffsetY = { -it }
            ) + fadeIn(animationSpec = easingAnimation()),
            exit = slideOutVertically(
                animationSpec = easingAnimation(),
                targetOffsetY = { -it }
            ) + fadeOut(animationSpec = easingAnimation())
        ) {
            currentSnackbarData?.let { data ->
                MSnackbarContent(
                    data = data,
                    onDismiss = { state.dismiss() },
                    modifier = Modifier
                        .padding(
                            top = statusBarPadding + TOP_PADDING,
                            start = HORIZONTAL_PADDING,
                            end = HORIZONTAL_PADDING
                        )
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Stable
class MSnackbarState {

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

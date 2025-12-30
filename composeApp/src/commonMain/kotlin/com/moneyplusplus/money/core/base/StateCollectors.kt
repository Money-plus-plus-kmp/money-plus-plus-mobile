package com.moneyplusplus.money.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <S : UiState, I : UiIntent, E : UiEffect> BaseViewModel<S, I, E>.collectState(): S {
    val state by state.collectAsStateWithLifecycle()
    return state
}

@Composable
fun <T> Flow<T>.collectEffect(onEffect: (T) -> Unit) {
    LaunchedEffect(Unit) {
        collectLatest { onEffect(it) }
    }
}
package com.moneyplusplus.presentation.feature.home

import com.moneyplusplus.presentation.base.BaseViewModel

class HomeViewModel(): BaseViewModel<HomeUiState, HomeIntent, HomeEffect>(
    initialState = HomeUiState()
) {
    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
            TODO() -> {}
        }
    }
}
package com.moneyplusplus.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyplusplus.domain.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

import com.moneyplusplus.domain.repository.AccountRepository

@OptIn(ExperimentalUuidApi::class)
class AccountViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AccountState())
    val state: StateFlow<AccountState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                // Mocking a user profile (In real app, fetch from AuthRepository)
                val mockUser = User(
                    id = Uuid.random(),
                    email = "user@example.com",
                    name = "Antigravity User"
                )
                
                // Fetch Account Data
                val account = accountRepository.getAccount(mockUser.id.toString())
                
                _state.update { it.copy(userProfile = mockUser, account = account, isLoading = false) }
            } catch (e: Exception) {
               _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun handleIntent(intent: AccountIntent) {
        when (intent) {
            AccountIntent.EditProfile -> {
                // Handle navigation to edit profile
            }
            is AccountIntent.NavigateToSettings -> {
                // Handle navigation to settings
            }
        }
    }
}

package com.moneyplusplus.presentation.account

import com.moneyplusplus.domain.entity.User
import com.moneyplusplus.domain.repository.AccountRepository
import com.moneyplusplus.presentation.base.BaseViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AccountViewModel(
    private val accountRepository: AccountRepository
) : BaseViewModel<AccountState, AccountIntent, AccountEffect>(AccountState()) {
    // TODO: Replace this with the authenticated user from AuthRepository/session state.
    private val mockUser = User(
        id = Uuid.parse("00000000-0000-0000-0000-000000000001"),
        email = "user@example.com",
        name = "Antigravity User"
    )

    init {
        loadData()
    }

    private fun loadData() {
        tryExecute(
            onStart = { updateState { copy(isLoading = true, error = null) } },
            block = { accountRepository.getAccount(mockUser.id.toString()) },
            onSuccess = { account ->
                updateState {
                    copy(
                        userProfile = mockUser.toUi(),
                        account = account.toUi(),
                        isLoading = false
                    )
                }
            },
            onError = { throwable ->
                updateState { copy(error = throwable.message, isLoading = false) }
            }
        )
    }

    override fun handleIntent(intent: AccountIntent) {
        when (intent) {
            AccountIntent.EditProfile -> {
                sendEffect(AccountEffect.NavigateToEditProfile)
            }
            is AccountIntent.NavigateToSettings -> {
                sendEffect(AccountEffect.NavigateToSettings(intent.type))
            }
        }
    }
}

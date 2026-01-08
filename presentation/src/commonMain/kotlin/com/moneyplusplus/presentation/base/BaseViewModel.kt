package com.moneyplusplus.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyplusplus.domain.exception.AppException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import money.presentation.generated.resources.Res
import org.jetbrains.compose.resources.StringResource

abstract class BaseViewModel<State : UiState, Intent : UiIntent, Effect : UiEffect>(
    initialState: State
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    protected val currentState: State get() = _state.value

    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    abstract fun handleIntent(intent: Intent)

    protected fun updateState(reduce: State.() -> State) {
        _state.update { it.reduce() }
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    protected fun <T> tryExecute(
        block: suspend () -> T,
        onStart: suspend () -> Unit = {},
        onSuccess: suspend (T) -> Unit = {},
        onError: suspend (ErrorState) -> Unit = {},
        onCompleted: suspend () -> Unit = {},
    ) {
        viewModelScope.launch {
            onStart()
            runCatching { block() }
                .onSuccess { onSuccess(it) }
                .onFailure { onError(mapExceptionToErrorState(it)) }
            onCompleted()
        }
    }

    private fun mapExceptionToErrorState(throwable: Throwable): ErrorState {
        val appException = throwable as AppException
        return ErrorState(
            message = mapExceptionsToMessage(appException),
            exception = appException
        )

    }

    private fun mapExceptionsToMessage(exception: AppException): StringResource {
        when(exception){
            is AppException.AuthenticationException.EmptyEmail -> TODO()
            is AppException.AuthenticationException.EmptyName -> TODO()
            is AppException.AuthenticationException.EmptyPassword -> TODO()
            is AppException.AuthenticationException.InvalidEmail -> TODO()
            is AppException.AuthenticationException.InvalidPassword -> TODO()
            is AppException.AuthenticationException.InvalidUserCredential -> TODO()
            is AppException.AuthenticationException.InvalidUserName -> TODO()
            is AppException.NetworkException.NoInternetException -> TODO()
            is AppException.NetworkException.UnAuthorizedException -> TODO()
            is AppException.NetworkException.UnKnownNetworkException -> TODO()
            is AppException.UnknownException -> TODO()
        }
    }

}

/**
 * Interface for all one-time side effects.
 * Implement this in your feature's Effect sealed interface.
 */
interface UiEffect


/**
 * Interface for all user intents/events.
 * Implement this in your feature's Event sealed interface.
 */
interface UiIntent


/**
 * Interface for all UI states.
 * Implement this in your feature's State data class.
 */
interface UiState

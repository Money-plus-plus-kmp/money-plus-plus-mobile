package com.moneyplusplus.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyplusplus.domain.exception.AppException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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


    private fun mapExceptionsToMessage(appException: AppException): StringResource =
        when (appException) {
            AppException.AuthException.EmailAlreadyExists -> TODO()
            AppException.AuthException.InvalidCredentials -> TODO()
            AppException.ValidationException.Email.Empty -> TODO()
            AppException.ValidationException.Email.InvalidEmail -> TODO()
            AppException.ValidationException.Password.Empty -> TODO()
            AppException.ValidationException.Password.InvalidPassword -> TODO()
            AppException.ValidationException.Name.Empty -> TODO()
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

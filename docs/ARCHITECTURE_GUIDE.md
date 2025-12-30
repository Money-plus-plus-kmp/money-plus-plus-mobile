# MVI Architecture Guide

This project follows the **Model-View-Intent (MVI)** architecture pattern. This ensures a unidirectional data flow, making the state predictable and the code easier to test and maintain.

## Core Components

The architecture is built around three main concepts:

1.  **State (`UiState`)**: Represents the current state of the UI at any given point in time. It is immutable.
2.  **Intent (`UiIntent`)**: Represents an action taken by the user or an event triggered by the system (e.g., "LoginButtonClicked", "ScreenOpened").
3.  **Effect (`UiEffect`)**: Represents a one-time side effect, such as showing a toast, navigating between screens, or showing an error message.

## Base Class Reference

### BaseViewModel

All ViewModels should extend `BaseViewModel`. This base class handles the boilerplate for managing state, processing intents, and emitting effects.

```kotlin
package com.moneyplusplus.money.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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

    protected fun launch(block: suspend () -> Unit) {
        viewModelScope.launch { block() }
    }
}
```

### Core Interfaces

```kotlin
/**
 * Interface for all UI states.
 * Implement this in your feature's State data class.
 */
interface UiState

/**
 * Interface for all user intents/events.
 * Implement this in your feature's Event sealed interface.
 */
interface UiIntent

/**
 * Interface for all one-time side effects.
 * Implement this in your feature's Effect sealed interface.
 */
interface UiEffect
```

### UI Extensions

Use these extensions in your Composable functions to easily observe state and effects.

```kotlin
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
```

---

## Usage Example: Authentication Feature

Here is a complete example of how to implement a Login feature using this architecture.

### Directory Structure

```text
auth/
├── login/
│   ├── LoginContract.kt   // Holds State, Intent, Effect definitions
│   ├── LoginViewModel.kt  // Logic and State management
│   └── LoginScreen.kt     // UI Composable
└── signup/
    ├── SignUpContract.kt
    ├── SignUpViewModel.kt
    └── SignUpScreen.kt
```

### 1. Define the Contract (`LoginContract.kt`)

Define the State, Intents, and Effects for your screen.

```kotlin
data class LoginState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState

sealed interface LoginIntent : UiIntent {
    data class EmailChanged(val email: String) : LoginIntent
    data object LoginClicked : LoginIntent
}

sealed interface LoginEffect : UiEffect {
    data object NavigateToHome : LoginEffect
    data class ShowToast(val message: String) : LoginEffect
}
```

### 2. Implement the ViewModel (`LoginViewModel.kt`)

Implement `handleIntent` to process actions and update the state or send effects.

```kotlin
class LoginViewModel : BaseViewModel<LoginState, LoginIntent, LoginEffect>(
    initialState = LoginState()
) {

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> updateState { copy(email = intent.email) }
            LoginIntent.LoginClicked -> login()
        }
    }

    private fun login() {
        launch {
            updateState { copy(isLoading = true, error = null) }
            try {
                // Simulate network call
                // performLogin(currentState.email)
                
                sendEffect(LoginEffect.NavigateToHome)
            } catch (e: Exception) {
                updateState { copy(error = e.message) }
                sendEffect(LoginEffect.ShowToast("Login Failed"))
            } finally {
                updateState { copy(isLoading = false) }
            }
        }
    }
}
```

### 3. Build the UI (`LoginScreen.kt`)

Use `collectState()` to observe the state and `collectEffect()` to handle side effects.

```kotlin
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(), // Assuming Koin is used
    onNavigateHome: () -> Unit
) {
    val state = viewModel.collectState()
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.effect.collectEffect { effect ->
        when (effect) {
            LoginEffect.NavigateToHome -> onNavigateHome()
            is LoginEffect.ShowToast -> {
                snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Column {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        
        TextField(
            value = state.email,
            onValueChange = { viewModel.handleIntent(LoginIntent.EmailChanged(it)) }
        )
        
        Button(
            onClick = { viewModel.handleIntent(LoginIntent.LoginClicked) }
        ) {
            Text("Login")
        }
        
        state.error?.let {
            Text(text = it, color = Color.Red)
        }
    }
}
```

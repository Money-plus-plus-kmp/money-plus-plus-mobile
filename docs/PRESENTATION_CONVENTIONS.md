# Presentation Conventions

This document outlines the conventions for the presentation layer in our project. Sticking to these conventions ensures consistency and makes the codebase easier to navigate.

## Naming Conventions

### Feature Packages
Feature packages should be named in **snake_case** (or lowercase), representing the feature domain.
*   Example: `auth`, `user_profile`, `transaction_history`

### Files
Files within a feature package should follow a strict naming pattern based on the screen they represent.

1.  **Contract**: `<ScreenName>Contract.kt`
    *   Contains `UiState`, `UiIntent`, and `UiEffect` implementations.
2.  **ViewModel**: `<ScreenName>ViewModel.kt`
    *   Extends `BaseViewModel`.
3.  **Screen**: `<ScreenName>Screen.kt`
    *   Contains the main Composable function for the screen.

**Example for a "Login" screen:**
*   `LoginContract.kt`
*   `LoginViewModel.kt`
*   `LoginScreen.kt`

## File Structure

Group related files by their screen/feature. If a feature has multiple screens, create sub-packages for each screen.

**Example:**

```text
presentation/
├── auth/                 <-- Feature Component
│   ├── login/            <-- Screen Package
│   │   ├── LoginContract.kt
│   │   ├── LoginViewModel.kt
│   │   └── LoginScreen.kt
│   └── signup/
│       ├── SignUpContract.kt
│       ├── SignUpViewModel.kt
│       └── SignUpScreen.kt
├── dashboard/
│   ├── DashboardContract.kt
│   ├── DashboardViewModel.kt
│   └── DashboardScreen.kt
```

## Contract Definitions

Keep your State, Intent, and Effect definitions together in the Contract file. This provides a single source of truth for the screen's API.

```kotlin
// LoginContract.kt

data class LoginState(...) : UiState

sealed interface LoginIntent : UiIntent { ... }

sealed interface LoginEffect : UiEffect { ... }
```

## ViewModel Responsibilities

*   **Initialization**: Initialize the state in the constructor.
*   **Intent Handling**: Provide a concise `handleIntent` implementation that delegates to private methods.
*   **State Updates**: Use `updateState` to safely update the immutable state.
*   **Side Effects**: Use `sendEffect` for one-off events (navigation, toasts).

## Screen / Composable Responsibilities

*   **Observation**: Collect state using `collectState()`.
*   **Side Effects**: Handle effects using `collectEffect()`.
*   **Event Forwarding**: Forward user actions to the ViewModel via `viewModel.handleIntent(...)`.
*   **Navigation**: Navigation logic (actually changing the destination) should often happen in response to an `Effect`.

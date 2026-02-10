package com.moneyplusplus.money.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for the app.
 * Each route is a serializable object/class that uniquely identifies a destination.
 */
@Serializable
sealed interface NavigationRoute {

    @Serializable
    data object Login : NavigationRoute

    @Serializable
    data object Home : NavigationRoute

    @Serializable
    data object ForgetPassword : NavigationRoute

    @Serializable
    data object CreateAccount : NavigationRoute

    @Serializable
    data object AddIncome : NavigationRoute
}

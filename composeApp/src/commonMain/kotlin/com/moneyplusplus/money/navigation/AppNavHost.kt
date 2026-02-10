package com.moneyplusplus.money.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moneyplusplus.presentation.auth.create_account.CreateAccountScreen
import com.moneyplusplus.presentation.feature.forgetPassword.ForgetPasswordScreen
import com.moneyplusplus.presentation.HomeScreen
import com.moneyplusplus.presentation.feature.income.screens.addincome.AddIncomeScreen
import com.moneyplusplus.presentation.login.LoginScreen

/**
 * Main navigation host for the app.
 * Defines all navigation destinations and their composable content.
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login
    ) {
        composable<NavigationRoute.Login> {
            LoginScreen(
                onNavigateHome = {
                    navController.navigate(NavigationRoute.Home) {
                        popUpTo(NavigationRoute.Login) { inclusive = true }
                    }
                },
                onNavigateForgetPassword = {
                    navController.navigate(NavigationRoute.ForgetPassword)
                },
                onNavigateCreateAccount = {
                    navController.navigate(NavigationRoute.CreateAccount)
                }
            )
        }

        composable<NavigationRoute.Home> {
            HomeScreen(
                onAddIncome = {
                    navController.navigate(NavigationRoute.AddIncome)
                }
            )
        }

        composable<NavigationRoute.ForgetPassword> {
            ForgetPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<NavigationRoute.CreateAccount> {
            CreateAccountScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToAccountSetup = {
                    // Navigate to home for now as AccountSetup screen is not available
                    navController.navigate(NavigationRoute.Home) {
                        popUpTo(NavigationRoute.Login) { inclusive = true }
                    }
                }
            )
        }

        composable<NavigationRoute.AddIncome> {
            AddIncomeScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

    }
}

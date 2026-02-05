package com.moneyplusplus.money.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moneyplusplus.presentation.auth.create_account.CreateAccountScreen
import com.moneyplusplus.presentation.feature.forgetPassword.ForgetPasswordScreen
import com.moneyplusplus.presentation.login.LoginScreen
import com.moneyplusplus.presentation.statistics.StatisticsScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Statistics
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
            // HomeScreen()
        }
        composable<NavigationRoute.Statistics> {
            StatisticsScreen()
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
    }
}

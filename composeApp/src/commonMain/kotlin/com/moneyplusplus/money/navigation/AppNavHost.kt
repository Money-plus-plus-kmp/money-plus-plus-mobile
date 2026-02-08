package com.moneyplusplus.money.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moneyplusplus.presentation.auth.create_account.CreateAccountScreen
import com.moneyplusplus.presentation.feature.forgetPassword.ForgetPasswordScreen
import com.moneyplusplus.presentation.HomeScreen
import com.moneyplusplus.presentation.AccountScreen
import com.moneyplusplus.presentation.login.LoginScreen

@Composable
fun AppNavHost(
    appVersion: String,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Account
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
                onNavigateToAccount = { navController.navigate(NavigationRoute.Account) }
            )
        }

        composable<NavigationRoute.Account> {
            AccountScreen(appVersion = appVersion)
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
                    navController.navigate(NavigationRoute.Home) {
                        popUpTo(NavigationRoute.Login) { inclusive = true }
                    }
                }
            )
        }
    }
}

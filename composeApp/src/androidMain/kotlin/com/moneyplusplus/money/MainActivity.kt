package com.moneyplusplus.money

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.auth.create_account.CreateAccountScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MoneyTheme {
                CreateAccountScreen(
                    onBackClick = {},
                    onNavigateToAccountSetup = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MoneyTheme {
        CreateAccountScreen(
            onBackClick = {},
            onNavigateToAccountSetup = {}
        )
    }
}
package com.moneyplusplus.money

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.moneyplusplus.presentation.AccountScreen
import com.moneyplusplus.design_system.theme.theme.MoneyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MoneyTheme {
                AccountScreen()
            }
        }
    }
}

package com.moneyplusplus.money

import androidx.compose.runtime.Composable
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.presentation.feature.transaction.TransactionScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MoneyTheme {
        TransactionScreen()
    }
}
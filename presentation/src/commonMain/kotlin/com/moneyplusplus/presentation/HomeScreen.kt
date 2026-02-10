package com.moneyplusplus.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.component.button.Button
import com.moneyplusplus.design_system.component.snackbar.LocalMSnackbarState
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun HomeScreen(
    onAddIncome: () -> Unit
) {
    val snackbar = LocalMSnackbarState.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { snackbar.showSuccess("Profile updated!") }) {
            Text("Save", style = Theme.typography.title.medium)
        }

        Button(onClick = { snackbar.showError("Connection failed") }) {
            Text("Delete", style = Theme.typography.title.medium)
        }

        Button(onClick = onAddIncome) {
            Text("Add Income", style = Theme.typography.title.medium)
        }

    }
}

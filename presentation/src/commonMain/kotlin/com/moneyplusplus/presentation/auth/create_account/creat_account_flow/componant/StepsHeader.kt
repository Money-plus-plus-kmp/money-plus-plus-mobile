package com.moneyplusplus.presentation.auth.create_account.creat_account_flow.componant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun StepsHeader(
    modifier: Modifier = Modifier,
    currentStep: Int,
    description: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        StepProgressBar(
            currentStep = currentStep,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Step ${currentStep + 1} of 3",
            style = Theme.typography.label.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Let’s set up your account",
            style = Theme.typography.heading.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = description,
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )
    }
}

package com.moneyplusplus.presentation.feature.forgetPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.feature.forgetPassword.component.TopAppBar
import com.moneyplusplus.presentation.feature.forgetPassword.content.ForgetPasswordContent
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.background_pattern
import money.presentation.generated.resources.enter_your_email_to_reset_password
import money.presentation.generated.resources.forget_your_password
import money.presentation.generated.resources.ic_background_pattern
import money.presentation.generated.resources.lock
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForgetPasswordScreen(
    modifier: Modifier = Modifier,
    isEnable: Boolean = false
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colorScheme.surface.surface),
    ) {
        TopAppBar()

        ForgetPasswordHeader()

        Text(
            text = stringResource(Res.string.forget_your_password),
            style = Theme.typography.heading.medium,
            modifier = Modifier.padding(
                top = 12.dp,
                start = 16.dp,
                bottom = 4.dp
            )
        )

        Text(
            text = stringResource(Res.string.enter_your_email_to_reset_password),
            style = Theme.typography.body.small,
            modifier = Modifier.padding(
                start = 16.dp,
                bottom = 16.dp
            )

        )

        ForgetPasswordContent(
            isEnabled = isEnable
        )
    }
}
@Composable
private fun ForgetPasswordHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_background_pattern),
            contentDescription = stringResource(Res.string.background_pattern),
        )

        Image(
            painter = painterResource(Res.drawable.lock),
            contentDescription = stringResource(Res.string.lock),
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(top = 25.dp)
                .size(width = 82.dp, height = 112.dp)
        )
    }
}

@Composable
@Preview
fun ForgetPasswordPreview() {
    MoneyTheme {
        ForgetPasswordScreen()
    }
}
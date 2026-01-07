package com.moneyplusplus.presentation.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.feature.component.BackgroundImage
import com.moneyplusplus.presentation.feature.component.TopAppBar
import money.presentation.generated.resources.Email_text
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.close
import money.presentation.generated.resources.email
import money.presentation.generated.resources.enter_your_password
import money.presentation.generated.resources.forget_your_password
import money.presentation.generated.resources.ic_email
import money.presentation.generated.resources.send_reset_link
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForgetPassword(
    modifier: Modifier = Modifier
) {
    val isButtonEnabled = mutableStateOf(true)

    val buttonBackground = if (isButtonEnabled.value) {
        Brush.verticalGradient(
            colors = listOf(
                Theme.colorScheme.primary.primary.copy(alpha = 0.6f),
                Theme.colorScheme.primary.primary
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Theme.colorScheme.disable,
                Theme.colorScheme.disable
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colorScheme.surface.surface)
    ) {
        TopAppBar()

        ForgetPasswordHeader()

        ForgetPasswordHeaderText()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        painterResource(Res.drawable.ic_email),
                        contentDescription = stringResource(Res.string.email),
                        modifier = Modifier.size(24.dp)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(Res.string.Email_text),
                        style = Theme.typography.label.medium
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(
                        brush = buttonBackground,
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = stringResource(Res.string.send_reset_link),
                    style = Theme.typography.label.medium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ForgetPasswordHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 23.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        BackgroundImage(
            modifier = Modifier.fillMaxWidth()
        )

        Image(
            painter = painterResource(Res.drawable.close),
            contentDescription = stringResource(Res.string.close),
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(top = 50.dp)
                .size(width = 82.dp, height = 112.dp)
                .offset(y = (-24).dp)
                .zIndex(1f)
        )
    }
}

@Composable
fun ForgetPasswordHeaderText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        Text(
            text = stringResource(Res.string.forget_your_password),
            style = Theme.typography.heading.medium,
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )

        Text(
            text = stringResource(Res.string.enter_your_password),
            style = Theme.typography.body.small,
            modifier = Modifier.padding(bottom = 16.dp)

        )
    }
}

@Composable
@Preview
fun ForgetPasswordPreview() {
    MoneyTheme {
        ForgetPassword()
    }
}
package com.moneyplusplus.presentation.feature.forgetPassword.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Email_text
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.email
import money.presentation.generated.resources.ic_email
import money.presentation.generated.resources.send_reset_link
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ForgetPasswordContent(
    isEnabled: Boolean,
    modifier: Modifier = Modifier
){
    val buttonBackground = if (isEnabled) {
        Theme.colorScheme.primary.primary
    } else {
        Theme.colorScheme.disable
    }
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //TODO: add text field
        OutlinedTextField(
            //TODO: add value and onValueChange
            value = "",
            onValueChange = {/*TODO*/},
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

        Spacer(
            modifier = Modifier.weight(1f)
        )
        //TODO add button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .background(
                    color = buttonBackground,
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

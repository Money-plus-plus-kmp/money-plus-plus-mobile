package com.moneyplusplus.presentation.feature.forgetPassword.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.arrow_left
import money.presentation.generated.resources.arrow_left_04
import money.presentation.generated.resources.forget_password
import money.presentation.generated.resources.money
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colorScheme.surface.surfaceLow)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.arrow_left_04),
            contentDescription = stringResource(Res.string.arrow_left),
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .background(Theme.colorScheme.surface.surfaceHigh)
                .padding(10.dp)
                .size(20.dp)
        )


        Text(
            text = stringResource(Res.string.forget_password),
            style = Theme.typography.title.small,
            color = Theme.colorScheme.title
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(Res.drawable.money),
            contentDescription = stringResource(Res.string.money),
            modifier = Modifier
                .padding(start = 8.dp)
                .size(width = 65.dp, height = 25.dp)
        )
    }
}
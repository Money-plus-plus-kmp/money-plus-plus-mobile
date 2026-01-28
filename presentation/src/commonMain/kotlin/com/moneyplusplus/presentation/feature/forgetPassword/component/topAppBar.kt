package com.moneyplusplus.presentation.feature.forgetPassword.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.image.Image
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
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppBar(
        modifier = modifier,
        title = stringResource(Res.string.forget_password),
        titleColor = Theme.colorScheme.title,
        leadingContent = {
            Icon(
                painter = painterResource(Res.drawable.arrow_left_04),
                contentDescription = stringResource(Res.string.arrow_left),
                modifier = Modifier.size(20.dp)
            )
        },
        onLeadingClick = onBackClick,
        trailingContent = {
            Image(
                painter = painterResource(Res.drawable.money),
                contentDescription = stringResource(Res.string.money),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(width = 65.dp, height = 25.dp)
            )
        }
    )
}
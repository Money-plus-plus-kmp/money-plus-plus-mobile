package com.moneyplusplus.presentation.feature.forgetPassword.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.image.Image
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.background_pattern
import money.presentation.generated.resources.ic_background_pattern
import money.presentation.generated.resources.lock
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ForgetPasswordHeader(
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

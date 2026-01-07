package com.moneyplusplus.presentation.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.bottom_background
import money.presentation.generated.resources.top_background
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BackgroundImage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colorScheme.surface.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.width(248.dp),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(Res.drawable.top_background),
                contentDescription = stringResource(Res.string.top_background),
                colorFilter = ColorFilter.tint(
                    Theme.colorScheme.primary.primary.copy(alpha = 0.1f)
                ),
                modifier = Modifier.size(width = 160.dp, height = 70.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(Res.drawable.bottom_background),
                contentDescription = stringResource(Res.string.bottom_background),
                colorFilter = ColorFilter.tint(
                    Theme.colorScheme.primary.primary.copy(alpha = 0.1f)
                ),
                modifier = Modifier.size(width = 250.dp, height = 70.dp)
            )
        }
    }
}
@Preview(widthDp = 500)
@Composable
fun BackgroundImagePreview() {
    BackgroundImage()
}
package com.moneyplusplus.design_system.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import money.design_system.generated.resources.Res
import money.design_system.generated.resources.ic_cancel
import money.design_system.generated.resources.ic_error
import money.design_system.generated.resources.ic_success
import money.design_system.generated.resources.snackbar_title_error
import money.design_system.generated.resources.snackbar_title_success
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val SNACKBAR_RADIUS = 16.dp
private val HORIZONTAL_PADDING = 16.dp
private val VERTICAL_PADDING = 12.dp
private val CONTENT_GAP = 12.dp
private val SHADOW_BLUR = 16.dp
private const val SHADOW_ALPHA = 0.08f

@Composable
fun MSnackbar(
    data: MSnackbarData,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    val colorScheme = Theme.colorScheme
    val typography = Theme.typography
    val shadowColor = data.type.shadowColor.copy(alpha = SHADOW_ALPHA)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = SHADOW_BLUR,
                shape = RoundedCornerShape(SNACKBAR_RADIUS),
                ambientColor = shadowColor,
                spotColor = shadowColor
            )
            .clip(RoundedCornerShape(SNACKBAR_RADIUS))
            .background(colorScheme.surface.surfaceLow)
            .padding(
                horizontal = HORIZONTAL_PADDING,
                vertical = VERTICAL_PADDING
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Leading Icon
            Icon(
                painter = painterResource(data.type.iconRes),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(CONTENT_GAP))

            // Content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(data.type.titleRes),
                    style = typography.title.small,
                    color = colorScheme.title
                )
                Text(
                    text = data.message,
                    style = typography.body.small,
                    color = colorScheme.body,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Dismiss Button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                )
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_cancel),
                contentDescription = null
            )
        }
    }
}


data class MSnackbarData(
    val type: MSnackbarType,
    val message: String
)


enum class MSnackbarType(
    val iconRes: DrawableResource,
    val titleRes: StringResource
) {
    SUCCESS(
        iconRes = Res.drawable.ic_success,
        titleRes = Res.string.snackbar_title_success
    ),
    ERROR(
        iconRes = Res.drawable.ic_error,
        titleRes = Res.string.snackbar_title_error
    );

    val shadowColor: Color
        @Composable
        get() = when (this) {
            SUCCESS -> Theme.colorScheme.accent.green.green
            ERROR -> Theme.colorScheme.accent.red.red
        }
}
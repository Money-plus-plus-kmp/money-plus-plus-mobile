package com.moneyplusplus.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.content.BaseButtonContent
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun OutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    iconSize: Dp = 20.dp,
    contentDescription: String? = null,
    iconStartPadding: Dp = 8.dp,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    contentColor: Color = Theme.colorScheme.title,
    disabledContentColor: Color = Theme.colorScheme.onPrimary.onPrimary,
    disabledContainerColor: Color = Theme.colorScheme.disable,
    containerColor: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Button(
        isEnabled = isEnabled,
        shape = shape,
        borderStroke = BorderStroke(width = 1.dp, color = Theme.colorScheme.stroke),
        contentColor = contentColor,
        containerColor = containerColor,
        disabledContentColor = disabledContentColor,
        disabledContainerColor = disabledContainerColor,
        contentPadding = contentPadding,
        onClick = onClick,
        isLoading = isLoading,
        modifier = modifier
    ) {
        BaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentDescription = contentDescription,
            contentColor = it
        )
    }
}
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
fun ErrorButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    trailingIcon: Painter? = null,
    contentDescription: String? = null,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = 8.dp,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = Theme.colorScheme.accent.red.redVariant,
    contentColor: Color = Theme.colorScheme.accent.red.red,
    disabledContainerColor: Color = Theme.colorScheme.disable,
    disabledContentColor: Color = Theme.colorScheme.onPrimary.onPrimary,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Button(
        isEnabled = isEnabled,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        disabledContainerColor = disabledContainerColor,
        contentPadding = contentPadding,
        shape = shape,
        onClick = onClick,
        isLoading = isLoading,
        borderStroke = BorderStroke(0.5.dp, color = Theme.colorScheme.stroke),
        modifier = modifier
    ) {
        BaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            contentDescription = contentDescription,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            contentColor = it
        )
    }
}
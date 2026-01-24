package com.moneyplusplus.design_system.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.content.BaseButtonContent
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    trailingIcon: Painter? = null,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    contentColor: Color = Theme.colorScheme.primary.primary,
    disabledContentColor: Color = Theme.colorScheme.disable,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    iconSize: Dp = 16.dp,
    iconStartPadding: Dp = 4.dp,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Button(
        onClick = onClick,
        isEnabled = isEnabled,
        contentPadding = contentPadding,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        shape = RoundedCornerShape(2.dp),
        isLoading = isLoading,
        modifier = modifier
    ){
        BaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            overflow = overflow,
            contentColor = it,
        )
    }
}
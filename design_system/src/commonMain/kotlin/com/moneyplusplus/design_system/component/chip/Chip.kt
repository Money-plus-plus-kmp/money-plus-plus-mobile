package com.moneyplusplus.design_system.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.animation.easingAnimation
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    painter: Painter? = null,
    modifier: Modifier = Modifier,
    iconSize: Dp = 16.dp,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    val containerColor by animateColorAsState(
        if (isSelected) Theme.colorScheme.primary.primary
        else Theme.colorScheme.surface.surfaceLow,
        animationSpec = easingAnimation()
    )
    val contentColor by animateColorAsState(
        if (isSelected) Theme.colorScheme.onPrimary.onPrimary
        else Theme.colorScheme.title,
        animationSpec = easingAnimation()
    )

    val horizontalPadding by animateDpAsState(
        if (isSelected) 16.dp
        else 12.dp,
        animationSpec = easingAnimation()
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(min = 32.dp)
            .clip(shape)
            .clickable(onClick = onClick)
            .background(containerColor)
            .then(
                if (isSelected) {
                    Modifier.innerShadow(
                        shape = shape,
                        shadow = Shadow(
                            radius = 12.dp,
                            color = Theme.colorScheme.innerShadow,
                            offset = DpOffset(0.dp, 4.dp)
                        )
                    )
                } else Modifier
            )
            .padding(
                vertical = 6.dp,
                horizontal = horizontalPadding
            )
    ) {
        painter?.let { iconPainter ->
            Icon(
                painter = iconPainter,
                modifier = Modifier.size(iconSize),
                contentDescription = null,
                tint = contentColor
            )
        }
        Text(
            text = text,
            style = Theme.typography.label.medium,
            color = contentColor,
        )
    }
}
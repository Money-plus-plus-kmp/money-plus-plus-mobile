package com.moneyplusplus.design_system.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.indicator.DotsProgressIndicator
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    loadingColors: List<Color> = listOf(
        Theme.colorScheme.primary.primary,
        Theme.colorScheme.primary.primary.copy(0.8f),
        Theme.colorScheme.primary.primary.copy(0.6f)
    ),
    containerColor: Color = Color.Transparent,
    disabledContainerColor: Color = Color.Transparent,
    contentColor: Color = Color.Transparent,
    disabledContentColor: Color = Color.Transparent,
    borderStroke: BorderStroke? = null,
    innerShadow: Shadow? = null,
    content: @Composable RowScope.(contentColor: Color) -> Unit
) {
    val buttonBackgroundColor by animateColorAsState(
        if (isEnabled) containerColor else disabledContainerColor,
        animationSpec = tween(
            durationMillis = 150,
            easing = EaseOut
        )
    )

    val buttonContentColor by animateColorAsState(
        if (isEnabled) contentColor else disabledContentColor
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .then(
                if (isLoading) modifier.wrapContentWidth(align = Alignment.Start)
                else modifier
            )
            .clip(shape)
            .then(
                if (isEnabled && !isLoading) Modifier.clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                )
                else Modifier
            )
            .heightIn(min = 52.dp)
            .background(color = buttonBackgroundColor)
            .then(
                if (innerShadow != null && isEnabled) {
                    Modifier.innerShadow(
                        shape = shape,
                        shadow = innerShadow
                    )
                } else Modifier
            )
            .then(
                if (borderStroke != null) Modifier.border(border = borderStroke, shape = shape)
                else Modifier
            )
            .animateContentSize(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                alignment = Alignment.Center
            )
            .padding(contentPadding)

    ) {
        if (isLoading) {
            if (loadingColors.isNotEmpty()) {
                DotsProgressIndicator(
                    colors = loadingColors,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
            }
        } else {
            content(buttonContentColor)
        }
    }
}
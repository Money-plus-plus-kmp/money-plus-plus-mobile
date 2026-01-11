package com.moneyplusplus.design_system.component.bottomNavigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.animation.easingAnimation
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun BottomNavigationBarItem(
    isSelected: Boolean,
    unselectedIcon: Painter,
    selectedIcon: Painter,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedIconTint by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.primary.primary else Theme.colorScheme.body,
        animationSpec = easingAnimation(durationMillis = 150)
    )
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(76.dp)
            .then(
                if (isSelected) Modifier
                else Modifier.clickable(
                    onClick = onClick,
                    indication = null,
                    interactionSource = interactionSource
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    alignment = Alignment.TopCenter
                )
        ) {
            Crossfade(
                targetState = isSelected,
                label = "IconSelectionCrossfade",
                animationSpec = easingAnimation()
            ) {
                Icon(
                    painter = if (isSelected) selectedIcon else unselectedIcon,
                    modifier = Modifier.size(24.dp),
                    contentDescription = title,
                    tint = animatedIconTint
                )
            }

            if (isSelected) {
                Text(
                    text = title,
                    style = Theme.typography.label.medium,
                    color = Theme.colorScheme.primary.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
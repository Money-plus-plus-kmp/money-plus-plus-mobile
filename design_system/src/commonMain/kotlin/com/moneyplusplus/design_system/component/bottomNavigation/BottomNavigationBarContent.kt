package com.moneyplusplus.design_system.component.bottomNavigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.moneyplusplus.design_system.theme.theme.Theme


@Composable
fun BottomNavigationBarContent(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (BottomNavigationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier.height(74.dp)) {
        val itemWidth = maxWidth / items.size
        val indicatorWidth = itemWidth - 40.dp
        val indicatorOffset by animateDpAsState(
            targetValue = selectedItemIndex * itemWidth,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            )
        )

        Row(
            Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationBarItem(
                    isSelected = index == selectedItemIndex,
                    selectedIcon = item.selectedIcon,
                    unselectedIcon = item.notSelectedIcon,
                    title = item.title,
                    onClick = { onItemClick(item) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Box(
            Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp)
                .offset(x = indicatorOffset)
                .size(indicatorWidth, 4.dp)
                .dropShadow(
                    shape = RoundedCornerShape(
                        topEnd = 4.dp,
                        topStart = 4.dp
                    ),
                    shadow = Shadow(
                        radius = 10.dp,
                        color = Theme.colorScheme.primary.primary.copy(0.2f),
                        offset = DpOffset(x = 0.dp, (-4).dp)
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topEnd = 4.dp,
                        topStart = 4.dp
                    )
                )
                .background(Theme.colorScheme.primary.primary)

        )
    }
}

data class BottomNavigationItem(
    val selectedIcon: Painter,
    val notSelectedIcon: Painter,
    val title: String
)
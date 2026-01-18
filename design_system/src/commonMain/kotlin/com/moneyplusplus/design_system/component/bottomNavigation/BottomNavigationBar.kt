package com.moneyplusplus.design_system.component.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int = 0,
    items: List<BottomNavigationItem>,
    onItemSelected: (Int) -> Unit
) {
    BottomNavigationBarContent(
        items = items,
        selectedItemIndex = selectedItemIndex,
        onItemClick = { item ->
            val index = items.indexOf(item)
            onItemSelected(index)
        },
        modifier = modifier.background(Theme.colorScheme.surface.surfaceLow)
    )
}
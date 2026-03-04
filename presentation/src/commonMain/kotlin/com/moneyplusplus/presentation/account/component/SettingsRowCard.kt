package com.moneyplusplus.presentation.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun SettingsRowCard(
    title: String,
    icon: Painter,
    showDivider: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 68.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Theme.colorScheme.surface.surfaceHigh,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Theme.colorScheme.body,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = title,
                style = Theme.typography.title.medium,
                color = Theme.colorScheme.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
        }

        if (showDivider) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0x1A1F1F1F))
            )
        }
    }
}

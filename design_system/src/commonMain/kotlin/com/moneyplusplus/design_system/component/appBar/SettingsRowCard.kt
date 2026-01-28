package com.moneyplusplus.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun SettingsRowCard(
    title: String,
    leadingContent: (@Composable () -> Unit)? = null,
    showDivider: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingContent != null) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Theme.colorScheme.surface.surfaceHigh,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    leadingContent()
                }
            }

            Text(
                text = title,
                style = Theme.typography.title.medium,
                color = Theme.colorScheme.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = if (leadingContent != null) 12.dp else 0.dp)
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

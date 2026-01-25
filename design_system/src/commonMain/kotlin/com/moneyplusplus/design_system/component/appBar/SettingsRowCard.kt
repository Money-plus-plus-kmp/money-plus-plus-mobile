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
    onClick: () -> Unit = {}
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

            Spacer(Modifier.width(12.dp))
        }

        Text(
            text = title,
            style = Theme.typography.title.medium,
            color = Theme.colorScheme.title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

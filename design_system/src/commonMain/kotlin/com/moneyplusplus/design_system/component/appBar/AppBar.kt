package com.moneyplusplus.design_system.component.appBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme

@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Theme.colorScheme.title,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    leadingContent: (@Composable () -> Unit)? = null,
    onLeadingClick: (() -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(contentPadding)
    ) {
        leadingContent?.let { content ->
            AppBarOptionContainer(
                onClick = onLeadingClick,
                modifier = Modifier.padding(end = 8.dp),
                content = content
            )
        }
        Text(
            text = title,
            color = titleColor,
            style = Theme.typography.title.medium
        )
        Spacer(modifier = Modifier.weight(1f))
        trailingContent?.let {
            Row(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                trailingContent()
            }
        }
    }
}
package com.moneyplusplus.design_system.component.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import money.design_system.generated.resources.Res
import money.design_system.generated.resources.ic_edit
import org.jetbrains.compose.resources.painterResource

@Composable
fun AccountInfoCard(
    name: String,
    email: String,
    initials: String,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(
                Theme.colorScheme.surface.surfaceLow,
                RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Theme.colorScheme.surface.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                style = Theme.typography.title.medium,
                color = Theme.colorScheme.title
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = Theme.typography.title.medium,
                color = Theme.colorScheme.title
            )

            Text(
                text = email,
                style = Theme.typography.body.medium,
                color = Theme.colorScheme.body
            )
        }

        Spacer(Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Theme.colorScheme.surface.surface)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_edit),
                contentDescription = null,
                tint = Theme.colorScheme.title,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

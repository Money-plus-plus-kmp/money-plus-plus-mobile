package com.moneyplusplus.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.design_system.generated.resources.Res
import money.design_system.generated.resources.ic_arrow_down
import money.design_system.generated.resources.ic_arrow_left
import money.design_system.generated.resources.ic_money_plus_plus
import money.design_system.generated.resources.ic_notification
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MppAppBar(
    title: String,
    textType: MppTextType,
    titleStyle: TextStyle,
    modifier: Modifier = Modifier,
    titleColor: Color = Theme.colorScheme.title,
    backgroundColor: Color = Theme.colorScheme.surface.surfaceLow,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 12.dp),
    onTitleClick: (() -> Unit) = {},
    onLeadingClick: (() -> Unit) = {},
    leadingIcon: Painter? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    when (textType) {
        MppTextType.Title -> {
            MppAppBarTitleContent(
                title = title,
                titleStyle = titleStyle,
                titleColor = titleColor,
                leadingIcon = leadingIcon,
                onLeadingClick = onLeadingClick,
                trailingContent = trailingContent,
                backgroundColor = backgroundColor,
                contentPadding = contentPadding,
                modifier = modifier,
            )
        }

        MppTextType.DatePicker -> {
            MppAppBarDatePickerContent(
                title = title,
                onTitleClick = onTitleClick,
                titleStyle = titleStyle,
                titleColor = titleColor,
                trailingContent = trailingContent,
                backgroundColor = backgroundColor,
                contentPadding = contentPadding,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun MppAppBarTitleContent(
    title: String,
    titleStyle: TextStyle,
    titleColor: Color,
    backgroundColor: Color,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onLeadingClick: () -> Unit,
    leadingIcon: Painter? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.let { icon ->
            Box(
                modifier = modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
                    .background(
                        color = Theme.colorScheme.surface.surface,
                        shape = CircleShape
                    ).clip(CircleShape)
                    .clickable(onClick = onLeadingClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Theme.colorScheme.title,
                )
            }
        }
        Text(
            text = title,
            style = titleStyle,
            color = titleColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        trailingContent?.invoke()
    }
}

@Composable
private fun MppAppBarDatePickerContent(
    title: String,
    titleStyle: TextStyle,
    titleColor: Color,
    backgroundColor: Color,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onTitleClick: () -> Unit,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = modifier
                .padding(end = 8.dp)
                .background(
                    color = Theme.colorScheme.surface.surface,
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .clickable(onClick = onTitleClick)
                .padding(horizontal = 8.dp, vertical = 5.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.5.dp)
        ) {
            Text(
                text = title,
                style = titleStyle,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_down),
                contentDescription = null,
                tint = Theme.colorScheme.title,
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.TopEnd
        ) {
            trailingContent?.invoke()
        }
    }
}

enum class MppTextType {
    Title,
    DatePicker,
}

@Composable
@Preview
fun MppAppBarTitlePreview() {
    MoneyTheme {
        Column {
            MppAppBar(
                title = "Screen title",
                textType = MppTextType.Title,
                titleStyle = Theme.typography.title.small,
                leadingIcon = painterResource(Res.drawable.ic_arrow_left),
                trailingContent = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = Theme.colorScheme.surface.surface,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_notification),
                            contentDescription = null,
                            tint = Theme.colorScheme.title,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            MppAppBar(
                title = "Screen title",
                textType = MppTextType.Title,
                titleStyle = Theme.typography.title.medium,
                leadingIcon = painterResource(Res.drawable.ic_arrow_left),
                trailingContent = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_notification),
                        contentDescription = null,
                        tint = Theme.colorScheme.title,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun MppAppBarDataPickerPreview() {
    MoneyTheme {
        MppAppBar(
            title = "September, 2025",
            textType = MppTextType.DatePicker,
            titleStyle = Theme.typography.label.small,
            trailingContent = {
                Icon(
                    painter = painterResource(Res.drawable.ic_money_plus_plus),
                    contentDescription = null,
                    tint = Theme.colorScheme.primary.primary,
                )
            }
        )
    }
}
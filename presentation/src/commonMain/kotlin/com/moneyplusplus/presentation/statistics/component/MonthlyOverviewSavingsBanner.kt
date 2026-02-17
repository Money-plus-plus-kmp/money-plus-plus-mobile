package com.moneyplusplus.presentation.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.statistics.util.AmountFormatter
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.ic_confetti
import money.presentation.generated.resources.savings_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val BannerCornerRadius = 12.dp
private val BannerHorizontalPadding = 16.dp
private val BannerVerticalPadding = 4.dp
private val BannerIconSize = 12.dp
private val BannerIconSpacing = 4.dp

@Composable
internal fun MonthlyOverviewSavingsBanner(
    savings: Double,
    currency: String,
    modifier: Modifier = Modifier,
) {
    val bannerShape = remember {
        RoundedCornerShape(
            bottomStart = BannerCornerRadius,
            bottomEnd = BannerCornerRadius,
        )
    }

    val secondaryVariant = Theme.colorScheme.secondary.variant
    val secondaryColor = Theme.colorScheme.secondary.secondary

    val formattedSavings = AmountFormatter.formatWithCommas(savings)
    val message = stringResource(
        Res.string.savings_message,
        formattedSavings,
        currency,
    )

    Row(
        modifier = modifier
            .clip(bannerShape)
            .background(secondaryVariant)
            .padding(horizontal = BannerHorizontalPadding, vertical = BannerVerticalPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_confetti),
            contentDescription = null,
            modifier = Modifier.size(BannerIconSize),
            colorFilter = ColorFilter.tint(secondaryColor),
        )

        Spacer(modifier = Modifier.width(BannerIconSpacing))

        Text(
            text = message,
            style = Theme.typography.label.xSmall,
            color = secondaryColor,
        )
    }
}

package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.add_transaction
import money.presentation.generated.resources.add_your_first
import money.presentation.generated.resources.img_record
import money.presentation.generated.resources.no_transaction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyTransactionsView(
    modifier: Modifier = Modifier,
    onAddTransactionClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.img_record),
            contentDescription = stringResource(Res.string.no_transaction),
            Modifier.width(96.dp).dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 20.dp,
                    color = Theme.colorScheme.accent.red.red.copy(alpha = 0.15f),
                    spread = 1.dp,
                )
            )
        )

        Text(
            stringResource(Res.string.no_transaction),
            style = Theme.typography.title.small,
            color = Theme.colorScheme.title,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            stringResource(Res.string.add_your_first),
            style = Theme.typography.body.small,
            color = Theme.colorScheme.body,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)

        )
        PrimaryButton(
            text = stringResource(Res.string.add_transaction),
            onClick = onAddTransactionClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionTopAppBarPreview() {
    MoneyTheme {
        EmptyTransactionsView()
    }
}
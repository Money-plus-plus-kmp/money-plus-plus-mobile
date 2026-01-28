package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.model.TransactionUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionItem(
    transaction: TransactionUiModel,
    modifier: Modifier = Modifier
) {
    val signColor = if (transaction.isExpense)
        Theme.colorScheme.accent.red.red
    else
        Theme.colorScheme.accent.green.green
    val amountTextColor = Theme.colorScheme.title
    val amountText = remember(
        transaction.amount,
        transaction.isExpense,
        signColor,
        amountTextColor
    ) {
        buildAnnotatedString {
            withStyle(SpanStyle(color = signColor)) {
                append(if (transaction.isExpense) "-" else "+")
            }
            withStyle(
                style = SpanStyle(color = amountTextColor)
            ) {
                append(transaction.amount)
            }
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = transaction.title,
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(5f),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.weight(2f))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = amountText,
                style = Theme.typography.label.medium
            )

            Text(
                text = transaction.dateFormated,
                style = Theme.typography.label.small,
                color = Theme.colorScheme.hint
            )
        }
    }
}


@Preview(heightDp = 100, showBackground = true)
@Composable
private fun TransactionItemPreview() {
    MoneyTheme {
        Column {
            TransactionItem(
                transaction = TransactionUiModel(
                    id = "1",
                    title = "Salary",
                    amount = "30,000 EGP",
                    isExpense = false,
                    dateFormated = "27/01/2026"
                )
            )
            TransactionItem(
                transaction = TransactionUiModel(
                    id = "2",
                    title = "Travel",
                    amount = "50,000 EGP",
                    isExpense = true,
                    dateFormated = "27/01/2026"
                )
            )
        }


    }
}
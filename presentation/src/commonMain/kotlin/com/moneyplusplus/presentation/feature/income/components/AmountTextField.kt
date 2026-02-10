package com.moneyplusplus.presentation.feature.income.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.component.textField.TextField
import com.moneyplusplus.design_system.theme.theme.Theme
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.money_01
import org.jetbrains.compose.resources.painterResource

@Composable
fun AmountTextField(
    modifier: Modifier = Modifier,
    amount: Int?,
    currency: String?,
    setAmount: (Int?) -> Unit
) {
    Box(modifier = modifier) {
        TextField(
            value = amount?.toString().orEmpty(),
            hint = "Amount",
            leadingIcon = painterResource(Res.drawable.money_01),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChanged = { setAmount(it.toIntOrNull()) }
        )

        currency?.let {
            Text(
                text = currency,
                style = Theme.typography.label.small,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = Theme.colorScheme.surface.surface)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}
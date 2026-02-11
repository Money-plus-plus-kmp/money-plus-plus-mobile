package com.moneyplusplus.presentation.auth.create_account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

fun Modifier.selectionTriangle(
    isVisible: Boolean,
    color: Color
): Modifier = this.then(
    other = if (isVisible) {
        Modifier.drawBehind {
            val triangleWidth = 14.dp.toPx()
            val triangleHeight = size.height / 2.3f
            val startY = (size.height - triangleHeight) / 3.5f

            drawPath(
                path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(x = 0f, y = startY)
                    lineTo(x = triangleWidth, y = startY + triangleHeight / 2)
                    lineTo(x = 0f, y = startY + triangleHeight)
                    close()
                },
                color = color
            )
        }
    } else {
        Modifier
    }
)

@Preview(showBackground = true)
@Composable
fun SalaryDayDatePickerPreview() {
    MoneyTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .selectionTriangle(
                    isVisible = true,
                    color = Theme.colorScheme.primary.primary
                )
                .clickable { }
                .padding(start = 24.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "name",
                    style = Theme.typography.label.medium,
                    color = Theme.colorScheme.primary.primary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "code",
                    style = Theme.typography.label.medium,
                    color = Theme.colorScheme.primary.primary

                )
            }

            Text(
                text = "country",
                style = Theme.typography.label.small,
                color = Theme.colorScheme.primary.primary)
        }
    }
}
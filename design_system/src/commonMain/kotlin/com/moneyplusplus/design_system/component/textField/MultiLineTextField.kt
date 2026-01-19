package com.moneyplusplus.design_system.component.textField

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MultiLineTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    minLines: Int = 5,
    maxLines: Int = 5,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChanged: (Boolean) -> Unit = {},
) {
    BasicTextField(
        value = value,
        hint = hint,
        onValueChanged = onValueChanged,
        modifier = modifier,
        singleLine = false,
        minLines = minLines,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        errorMessage = errorMessage,
        shape = shape,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onFocusChanged = onFocusChanged,
    )
}

@Preview(
    name = "MultiLineTextField - Default",
    showBackground = true
)
@Composable
private fun MultiLineTextFieldPreview() {
    MoneyTheme {
        var text by remember { mutableStateOf("") }

        MultiLineTextField(
            value = text,
            hint = "Write your message here...",
            onValueChanged = { text = it }
        )
    }
}

@Preview(
    name = "MultiLineTextField - With Error",
    showBackground = true
)
@Composable
private fun MultiLineTextFieldErrorPreview() {
    MoneyTheme {
        var text by remember { mutableStateOf("Some text") }

        MultiLineTextField(
            value = text,
            hint = "Description",
            onValueChanged = { text = it },
            isError = true,
            errorMessage = "This field is required"
        )
    }
}

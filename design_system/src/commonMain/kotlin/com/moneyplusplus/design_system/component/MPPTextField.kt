package com.moneyplusplus.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.utils.MppPreview
import com.moneyplusplus.design_system.theme.theme.Theme
import money.design_system.generated.resources.Res
import money.design_system.generated.resources.ic_eyes_close
import money.design_system.generated.resources.ic_eyes_open
import money.design_system.generated.resources.ic_profile_outline
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: DrawableResource? = null,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle? = null,
    readOnly: Boolean = false,
    colors: TextFieldColors = MppTextFieldDefaults.colors()
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    with(MppTextFieldDefaults) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                readOnly = readOnly,
                enabled = enabled,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation()
                else visualTransformation,
                textStyle = textStyle ?: Theme.typography.body.medium.copy(
                    color = colors.textColor(enabled, isError, isFocused)
                ),
                modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused }.border(
                        width = 1.dp,
                        color = colors.borderColor(enabled, isError, isFocused),
                        shape = MppTextFieldDefaults.shape
                    ).clip(MppTextFieldDefaults.shape).background(
                        colors.containerColor(enabled, isError, isFocused),
                        MppTextFieldDefaults.shape
                    ),
                cursorBrush = SolidColor(
                    colors.cursorColor(isError)
                ),
                decorationBox = { innerTextField ->
                    MppDecorationBox(
                        value = value,
                        placeholder = placeholder,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        isPassword = isPassword,
                        passwordVisible = passwordVisible,
                        onPasswordToggle = { passwordVisible = !passwordVisible },
                        colors = colors,
                        isFocused = isFocused,
                        innerTextField = innerTextField
                    )
                })
            AnimatedVisibility(
                visible = isError && !errorMessage.isNullOrEmpty()
            ) {
                Text(
                    text = errorMessage ?: "",
                    color = Theme.colorScheme.accent.red.red,
                    style = Theme.typography.label.small,
                )
            }
        }
    }
}


@Composable
private fun MppDecorationBox(
    value: String,
    placeholder: String?,
    leadingIcon: DrawableResource?,
    trailingIcon: @Composable (() -> Unit)?,
    singleLine: Boolean,
    enabled: Boolean,
    isError: Boolean,
    isPassword: Boolean,
    passwordVisible: Boolean,
    onPasswordToggle: () -> Unit,
    colors: TextFieldColors,
    isFocused: Boolean,
    innerTextField: @Composable () -> Unit
) {
    with(MppTextFieldDefaults) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier.background(
                colors.containerColor(enabled, isError, isFocused), MppTextFieldDefaults.shape
            ).fillMaxWidth().padding(
                start = if (leadingIcon != null) 16.dp else 0.dp,
                end = if (isPassword || isError || trailingIcon != null) 16.dp else 0.dp
            )
        ) {
            if (leadingIcon != null) Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = colors.leadingIconColor(enabled, isError, isFocused)
            )


            Box(
                modifier = Modifier.weight(1f).padding(
                    start = if (leadingIcon != null) 8.dp else 16.dp,
                    end = if (singleLine) 0.dp else 16.dp,
                    top = 12.dp,
                    bottom = 12.dp,
                )
            ) {
                if (value.isEmpty() && placeholder != null) Text(
                    text = placeholder,
                    style = Theme.typography.label.medium,
                    color = colors.placeholderColor(enabled, isError, isFocused),
                    maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                    overflow = if (singleLine) TextOverflow.Ellipsis else TextOverflow.Clip
                )

                innerTextField()
            }
            when {
                isPassword -> {
                    val image =
                        if (passwordVisible) painterResource(Res.drawable.ic_eyes_open) else painterResource(
                            Res.drawable.ic_eyes_close
                        )
                    IconButton(
                        onClick = onPasswordToggle,
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            painter = image,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Theme.colorScheme.hint
                        )
                    }
                }

                trailingIcon != null -> {
                    trailingIcon()
                }
            }
        }
    }
}


object MppTextFieldDefaults {
    val shape = RoundedCornerShape(16.dp)

    @Composable
    fun colors(
        focusedTextColor: Color = Theme.colorScheme.title,
        unfocusedTextColor: Color = Theme.colorScheme.title,
        disabledTextColor: Color = Theme.colorScheme.disable,
        errorTextColor: Color = Theme.colorScheme.title,
        focusedContainerColor: Color = Theme.colorScheme.surface.surfaceLow,
        unfocusedContainerColor: Color = Theme.colorScheme.surface.surfaceLow,
        disabledContainerColor: Color = Theme.colorScheme.surface.surfaceLow,
        errorContainerColor: Color = Theme.colorScheme.surface.surfaceLow,
        cursorColor: Color = Theme.colorScheme.primary.primary,
        errorCursorColor: Color = Color.Transparent,
        focusedIndicatorColor: Color = Color.Transparent,
        unfocusedIndicatorColor: Color = Color.Transparent,
        disabledIndicatorColor: Color = Theme.colorScheme.stroke,
        errorIndicatorColor: Color = Theme.colorScheme.accent.red.red,
        focusedLeadingIconColor: Color = Theme.colorScheme.hint,
        unfocusedLeadingIconColor: Color = Theme.colorScheme.hint,
        disabledLeadingIconColor: Color = Theme.colorScheme.hint,
        errorLeadingIconColor: Color = Theme.colorScheme.accent.red.red,
        focusedTrailingIconColor: Color = Theme.colorScheme.hint,
        unfocusedTrailingIconColor: Color = Theme.colorScheme.hint,
        disabledTrailingIconColor: Color = Theme.colorScheme.hint,
        errorTrailingIconColor: Color = Theme.colorScheme.hint,
        focusedLabelColor: Color = Theme.colorScheme.hint,
        unfocusedLabelColor: Color = Theme.colorScheme.hint,
        disabledLabelColor: Color = Theme.colorScheme.hint,
        errorLabelColor: Color = Theme.colorScheme.hint,
        focusedPlaceholderColor: Color = Theme.colorScheme.body,
        unfocusedPlaceholderColor: Color = Theme.colorScheme.body,
        disabledPlaceholderColor: Color = Theme.colorScheme.hint,
        errorPlaceholderColor: Color = Theme.colorScheme.accent.red.red,
        focusedSupportingTextColor: Color = Theme.colorScheme.body,
        unfocusedSupportingTextColor: Color = Theme.colorScheme.body,
        disabledSupportingTextColor: Color = Theme.colorScheme.disable,
        errorSupportingTextColor: Color = Theme.colorScheme.accent.red.red,
        selectionColors: androidx.compose.foundation.text.selection.TextSelectionColors? = null
    ): TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = unfocusedTextColor,
        disabledTextColor = disabledTextColor,
        errorTextColor = errorTextColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorContainerColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        selectionColors = selectionColors,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        disabledIndicatorColor = disabledIndicatorColor,
        errorIndicatorColor = errorIndicatorColor,
        focusedLeadingIconColor = focusedLeadingIconColor,
        unfocusedLeadingIconColor = unfocusedLeadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = errorLeadingIconColor,
        focusedTrailingIconColor = focusedTrailingIconColor,
        unfocusedTrailingIconColor = unfocusedTrailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = errorTrailingIconColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,
        focusedPlaceholderColor = focusedPlaceholderColor,
        unfocusedPlaceholderColor = unfocusedPlaceholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor,
        errorPlaceholderColor = errorPlaceholderColor,
        focusedSupportingTextColor = focusedSupportingTextColor,
        unfocusedSupportingTextColor = unfocusedSupportingTextColor,
        disabledSupportingTextColor = disabledSupportingTextColor,
        errorSupportingTextColor = errorSupportingTextColor,
    )

    fun TextFieldColors.cursorColor(isError: Boolean): Color = if (isError) errorCursorColor else cursorColor

    fun TextFieldColors.textColor(enabled: Boolean, isError: Boolean, isFocused: Boolean): Color = when {
        !enabled -> disabledTextColor
        isError -> errorTextColor
        isFocused -> focusedTextColor
        else -> unfocusedTextColor
    }

    fun TextFieldColors.borderColor(enabled: Boolean, isError: Boolean, isFocused: Boolean): Color = when {
        !enabled -> disabledIndicatorColor
        isError -> errorIndicatorColor
        isFocused -> focusedIndicatorColor
        else -> unfocusedIndicatorColor
    }

    fun TextFieldColors.containerColor(enabled: Boolean, isError: Boolean, isFocused: Boolean): Color = when {
        !enabled -> disabledContainerColor
        isError -> errorContainerColor
        isFocused -> focusedContainerColor
        else -> unfocusedContainerColor
    }

    fun TextFieldColors.leadingIconColor(enabled: Boolean, isError: Boolean, isFocused: Boolean): Color = when {
        !enabled -> disabledLeadingIconColor
        isError -> errorLeadingIconColor
        isFocused -> focusedLeadingIconColor
        else -> unfocusedLeadingIconColor
    }

    fun TextFieldColors.placeholderColor(enabled: Boolean, isError: Boolean, isFocused: Boolean): Color = when {
        !enabled -> disabledPlaceholderColor
        isError -> errorPlaceholderColor
        isFocused -> focusedPlaceholderColor
        else -> unfocusedPlaceholderColor
    }
}


@Preview
@Composable
private fun PreviewMppTextFields() = MppPreview {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(16.dp)
    ) {
        var text by remember { mutableStateOf("") }
        MppTextField(value = text, onValueChange = { text = it }, placeholder = "Enter your name", leadingIcon = Res.drawable.ic_profile_outline)

        var password by remember { mutableStateOf("") }
        MppTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPassword = true,
            leadingIcon = Res.drawable.ic_profile_outline
        )

        var email by remember { mutableStateOf("test") }
        MppTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            isError = true,
            errorMessage = "Error Message",
            leadingIcon = Res.drawable.ic_profile_outline
        )
    }
}
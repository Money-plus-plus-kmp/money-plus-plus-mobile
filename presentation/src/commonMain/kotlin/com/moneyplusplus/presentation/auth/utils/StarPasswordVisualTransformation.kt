package com.moneyplusplus.presentation.auth.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class StarPasswordVisualTransformation(
    private val mask: Char = STAR_PASSWORD_MASK
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val masked = mask.toString().repeat(text.text.length)
        return TransformedText(
            AnnotatedString(masked),
            OffsetMapping.Identity
        )
    }
}

const val STAR_PASSWORD_MASK = '*'

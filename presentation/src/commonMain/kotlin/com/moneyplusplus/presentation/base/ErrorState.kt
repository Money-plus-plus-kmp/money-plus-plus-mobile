package com.moneyplusplus.presentation.base

import com.moneyplusplus.domain.exception.AppException
import org.jetbrains.compose.resources.StringResource

data class ErrorState(
    val message: StringResource,
    val exception: AppException? = null
)

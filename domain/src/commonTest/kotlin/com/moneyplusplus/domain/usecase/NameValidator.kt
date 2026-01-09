package com.moneyplusplus.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import com.moneyplusplus.domain.usecase.validation.NameValidator
import com.moneyplusplus.domain.exception.AppException.ValidationException.Name.Empty

class NameValidatorTest {

    private val validator = NameValidator()

    @Test
    fun `blank name should throw Empty`() {
        assertFailsWith<Empty> {
            validator("")
        }
    }

    @Test
    fun `non-blank name should pass`() {
        validator(VALID_NAME)
        assertTrue(true)
    }
    private companion object {
        const val VALID_NAME = "Fatma"
}
}

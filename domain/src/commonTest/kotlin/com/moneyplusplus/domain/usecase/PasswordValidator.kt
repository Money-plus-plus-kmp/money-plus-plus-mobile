package com.moneyplusplus.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import com.moneyplusplus.domain.exception.ValidationException.Password.Empty
import com.moneyplusplus.domain.exception.ValidationException.Password.TooShort
import com.moneyplusplus.domain.usecase.validation.PasswordValidator

class PasswordValidatorTest {

    private val validator = PasswordValidator()

    @Test
    fun `blank password should throw Empty`() {
        assertFailsWith<Empty> {
            validator("")
        }
    }

    @Test
    fun `password shorter than 8 characters should throw InvalidPassword`() {
        assertFailsWith<TooShort> {
            validator(SHORT_PASSWORD)
        }
    }

    @Test
    fun `valid password should not throw exception`() {
        validator(VALID_PASSWORD)
        assertTrue(true)
    }

    private companion object {
        const val VALID_PASSWORD = "Abc12345"
        const val SHORT_PASSWORD = "A1b2"
    }
}

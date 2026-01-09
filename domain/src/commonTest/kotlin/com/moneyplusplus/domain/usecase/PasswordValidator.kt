package com.moneyplusplus.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import com.moneyplusplus.domain.exception.AppException.ValidationException.Password.Empty
import com.moneyplusplus.domain.exception.AppException.ValidationException.Password.InvalidPassword
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
        assertFailsWith<InvalidPassword> {
            validator(SHORT_PASSWORD)
        }
    }

    @Test
    fun `password without letters should throw InvalidPassword`() {
        assertFailsWith<InvalidPassword> {
            validator(NO_LETTERS_PASSWORD)
        }
    }

    @Test
    fun `password without digits should throw InvalidPassword`() {
        assertFailsWith<InvalidPassword> {
            validator(NO_DIGITS_PASSWORD)
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
        const val NO_LETTERS_PASSWORD = "12345678"
        const val NO_DIGITS_PASSWORD = "abcdefgh"
    }
}

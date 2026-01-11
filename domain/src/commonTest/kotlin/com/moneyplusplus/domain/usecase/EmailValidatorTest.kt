package com.moneyplusplus.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import com.moneyplusplus.domain.exception.ValidationException.Email.Empty
import com.moneyplusplus.domain.exception.ValidationException.Email.InvalidFormat
import com.moneyplusplus.domain.usecase.validation.EmailValidator

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun `blank email should throw Empty`() {
        assertFailsWith<Empty> {
            validator("")
        }
    }

    @Test
    fun `invalid email should throw InvalidEmail`() {
        assertFailsWith<InvalidFormat> {
            validator(INVALID_EMAIL)
        }
    }

    @Test
    fun `valid email should not throw exception`() {
        validator(VALID_EMAIL)
        assertTrue(true)
    }

    private companion object {
         const val VALID_EMAIL = "test@example.com"
         const val INVALID_EMAIL = "invalid-email"

    }
}

package com.zup.zupbank.domain.useCase.validate

import android.util.Patterns
import com.zup.zupbank.domain.ValidationResult
import com.zup.zupbank.domain.exception.ValidateException

class ValidateEmailUseCase {
    fun execute(email: String?): ValidationResult {
        if (email?.isBlank() == true) {
            return ValidationResult(
                successful = false,
                exception = ValidateException.EmailEmptyException(),
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email!!).matches()) {
            return ValidationResult(
                successful = false,
                exception = ValidateException.EmailInvalidException(),
            )
        }

        return ValidationResult(successful = true, exception = null)
    }
}

package com.zup.zupbank.domain.useCase.validate

import com.zup.zupbank.domain.ValidationResult
import com.zup.zupbank.domain.exception.ValidateException

class ValidateNameUseCase {
    fun execute(name: String?): ValidationResult {
        if (name?.isBlank() == true) {
            return ValidationResult(
                successful = false,
                exception = ValidateException.NameInvalidException(),
            )
        }

        return ValidationResult(successful = true, exception = null)
    }
}

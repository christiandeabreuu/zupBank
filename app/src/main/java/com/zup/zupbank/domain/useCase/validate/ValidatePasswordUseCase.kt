package com.zup.zupbank.domain.useCase.validate

import com.zup.zupbank.domain.ValidationResult
import com.zup.zupbank.domain.exception.ValidateException

class ValidatePasswordUseCase {

    fun execute(name: String?): ValidationResult {
        if (name == null || name.isBlank() || name.length < 5) {
            return ValidationResult(
                successful = false,
                exception = ValidateException.PasswordInvalidException("Senha não pode ter menos que 5 caracteres"),
            )
        }

        return ValidationResult(successful = true, exception = null)
    }
}

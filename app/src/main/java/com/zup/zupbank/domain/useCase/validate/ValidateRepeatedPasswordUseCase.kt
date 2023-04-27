package com.zup.zupbank.domain.useCase.validate

import com.zup.zupbank.domain.ValidationResult
import com.zup.zupbank.domain.exception.ValidateException

class ValidateRepeatedPasswordUseCase {

    fun execute(password: String?, repeatedPassword: String?): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                exception = ValidateException.RepeatPasswordException(),
            )
        }

        return ValidationResult(successful = true, exception = null)
    }
}

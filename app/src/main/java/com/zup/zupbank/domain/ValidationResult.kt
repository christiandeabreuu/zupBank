package com.zup.zupbank.domain

import com.zup.zupbank.domain.exception.ValidateException

data class ValidationResult(
    val successful: Boolean,
    val exception: ValidateException? = null
)

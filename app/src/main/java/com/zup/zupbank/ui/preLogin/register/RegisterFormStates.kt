package com.zup.zupbank.ui.preLogin.register

data class RegisterFormStates(
    val userNameError: String? = null,
    val userEmailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isDataValid: Boolean = false,
)

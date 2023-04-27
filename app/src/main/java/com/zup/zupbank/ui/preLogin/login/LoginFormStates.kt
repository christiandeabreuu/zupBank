package com.zup.zupbank.ui.preLogin.login

/**
 * Data validation state of the login form.
 */
data class LoginFormStates(
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isDataValid: Boolean = false
)

package com.zup.authenticator.core

sealed class AuthException(message: String) : Exception(message) {
    class AuthInvalidException(message: String) : AuthException(message = message)
}

package com.zup.zupbank.domain.exception

sealed class AuthException(override val message: String) : Exception(message) {
    class AuthNotFound : AuthException("Usuário não encontrado")
}

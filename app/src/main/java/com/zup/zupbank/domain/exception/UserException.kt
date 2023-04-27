package com.zup.zupbank.domain.exception

sealed class UserException(override val message: String) : Exception(message) {
    class UserNotCreated : UserException("Não foi possivel cadastrar usuário")
}

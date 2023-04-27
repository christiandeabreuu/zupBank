package com.zup.zupbank.domain.exception

sealed class ValidateException(override val message: String) : Exception(message) {
    class EmailInvalidException : ValidateException("E-mail inválido")
    class EmailEmptyException : ValidateException("E-mail vazio")

    class PasswordInvalidException(message: String?) :
        ValidateException(message ?: "Senha inválida")

    class PasswordEmptyException : ValidateException("Senha vazia")
    class RepeatPasswordException : ValidateException("Senhas diferentes")

    class NameInvalidException(message: String? = null) : ValidateException(message ?: "Nome vazio")

    class GenericException : ValidateException("Erro genérico")
}

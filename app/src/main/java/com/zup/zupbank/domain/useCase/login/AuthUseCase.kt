package com.zup.zupbank.domain.useCase.login

import com.zup.zupbank.domain.exception.AuthException
import com.zup.zupbank.domain.exception.ValidateException
import com.zup.zupbank.domain.model.User
import com.zup.zupbank.domain.repository.UserRepository
import com.zup.zupbank.domain.useCase.validate.ValidateEmailUseCase
import com.zup.zupbank.domain.useCase.validate.ValidatePasswordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthUseCase(
    private val repository: UserRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) {
    suspend fun execute(params: Params): Flow<User> = flow {
        // Valida o e-mail do usuário
        val validateEmail = validateEmailUseCase.execute(params.email)
        if (!validateEmail.successful) {
            throw validateEmail.exception ?: ValidateException.GenericException()
        }

        // Valida a senha do usuário
        val validatePassword = validatePasswordUseCase.execute(params.password)
        if (!validatePassword.successful) {
            throw validatePassword.exception ?: ValidateException.GenericException()
        }

        val user = repository.findUser(params.email!!, params.password!!)
            ?: throw AuthException.AuthNotFound()

        emit(user)
    }

    data class Params(val email: String?, val password: String?)
}

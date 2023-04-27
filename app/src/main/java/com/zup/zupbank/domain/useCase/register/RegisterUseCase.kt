package com.zup.zupbank.domain.useCase.register

import com.zup.zupbank.domain.exception.ValidateException
import com.zup.zupbank.domain.repository.UserRepository
import com.zup.zupbank.domain.useCase.validate.ValidateEmailUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateNameUseCase
import com.zup.zupbank.domain.useCase.validate.ValidatePasswordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val repository: UserRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
) {
    suspend fun execute(params: Params): Flow<Boolean> = flow {
        // Valida o nome do usuário
        val validateName = validateNameUseCase.execute(params.name)
        if (!validateName.successful) {
            throw validateName.exception ?: ValidateException.GenericException()
        }

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

        repository.create(params.name!!, params.password!!, params.email!!).catch {
        }.collect {
            emit(it)
        }
    }

    data class Params(val name: String?, val email: String?, val password: String?)
}

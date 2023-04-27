package com.zup.zupbank.data

import com.zup.zupbank.data.local.dao.UserDao
import com.zup.zupbank.data.local.model.UserEntity
import com.zup.zupbank.data.local.model.toUser
import com.zup.zupbank.domain.model.User
import com.zup.zupbank.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val service: UserDao) : UserRepository {

    override suspend fun findUser(email: String, password: String): User? {
        val user = service.find(email = email, password = password)

        return user?.toUser()
    }

    override suspend fun create(name: String, password: String, email: String): Flow<Boolean> =
        flow {
            try {
                val user = UserEntity(email, name, password)
                service.insert(user)
                emit(true)
            } catch (ex: Exception) {
                emit(false)
            }
        }
}

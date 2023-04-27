package com.zup.zupbank.domain.repository

import com.zup.zupbank.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun findUser(email: String, password: String): User?
    suspend fun create(name: String, password: String, email: String): Flow<Boolean>
}

package com.zup.zupbank.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zup.zupbank.data.local.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity)

    @Query("SELECT * FROM tb_user WHERE email = :email AND password = :password")
    fun find(email: String, password: String): UserEntity?
}

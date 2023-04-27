package com.zup.zupbank.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zup.zupbank.domain.model.User

@Entity(tableName = "tb_user")
data class UserEntity(
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "password")
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}

fun UserEntity.toUser() = User(
    id = this.id,
    name = this.name,
    email = this.email
)

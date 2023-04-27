package com.zup.zupbank.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.zup.zupbank.data.model.UserData
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class User(
    val id: Long,
    val name: String,
    val email: String
) : Parcelable

fun UserData.toUser(): User = User(
    id = this.id,
    name = this.name,
    email = this.email
)
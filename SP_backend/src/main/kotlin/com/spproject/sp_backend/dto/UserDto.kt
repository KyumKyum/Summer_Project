package com.spproject.sp_backend.dto

import com.spproject.sp_backend.model.User
import java.util.UUID


data class UserDto (
    var id: UUID?,
    var username:String? ,
    var ident: String?,
    var password: String?,
    var keyVal: String?
): Dto<User> {
    override fun toDomain(): User {
        return User(
            id = this.id,
            username = this.username ?: "UNNAMED",
            ident = this.ident ?: "DEFAULT",
            password = this.password ?: "DEFAULT",
            keyVal = this.keyVal ?: "Default"
        )
    }

    fun toDto(user: User?): UserDto? {
        if(user == null) return null

        return UserDto(
            user.id,
            user.username,
            user.ident,
            user.password,
            user.keyVal
        )
    }


}
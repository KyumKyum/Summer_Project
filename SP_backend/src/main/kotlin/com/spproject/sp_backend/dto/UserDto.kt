package com.spproject.sp_backend.dto

import com.spproject.sp_backend.model.User
import java.util.UUID


data class UserDto (
    var id: UUID,
    var username:String,
    var ident: String,
    var password: String,
    var keyVal: String
) {
    fun toEntity(): User {
        return User(id, username, ident, password, keyVal)
    }
}
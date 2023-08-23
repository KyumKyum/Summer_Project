package com.spproject.sp_backend.service.utils

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.model.User
import com.spproject.sp_backend.repository.UsersRepository
import org.springframework.stereotype.Component

@Component
class SignInUtils(
    private val usersRepository: UsersRepository,
    private val passwordUtils: PasswordUtils

) {
    fun checkValidSignIn(
        ident: String,
        password: String
    ): Boolean {
        if(ident.isEmpty()) return false;
        if(password.isEmpty()) return false;

        return true;
    }

    fun searchUser(
        ident: String,
    ): UserDto? {
        val user: User = usersRepository.findByIdent(ident) ?: return null

        return UserDto(
            user.id,
            user.username,
            user.ident,
            user.password,
            user.keyVal
        )
    }

    fun checkRightPassword(
        encrypted: String,
        plainText: String
    ): Boolean {
        return encrypted == passwordUtils.getEncryptedPassword(plainText)
    }
}
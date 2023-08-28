package com.spproject.sp_backend.service.signUp.utils

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.model.User
import com.spproject.sp_backend.repository.UsersRepository
import org.springframework.stereotype.Component

@Component
class SignUpUtils(
    private val usersRepository: UsersRepository,
) {

    fun checkValidUserInfo(user:UserDto): Boolean{
        //* check if input parameter is all valid.
        if(user.username == null) return false;
        if(user.ident == null) return false;
        if(user.password == null) return false;

        return true;
    }

    fun checkDuplicateIdentExists(ident: String): Boolean {
        val user: User? = usersRepository.findByIdent(ident)

        return user == null
    }

    fun checkDuplicateUsernameExists(username: String): Boolean {
        val user: User? = usersRepository.findByUsername(username)

        return user == null
    }

    fun createUser(user: UserDto): UserDto? {
        return try{
            val saved:User = usersRepository.save(user.toDomain())
            UserDto(
                saved.id,
                saved.username,
                saved.ident,
                saved.password,
                saved.keyVal
            )
        }catch (error:Error){
            null
        }
    }
}
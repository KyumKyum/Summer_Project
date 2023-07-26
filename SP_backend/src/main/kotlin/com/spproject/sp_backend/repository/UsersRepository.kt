package com.spproject.sp_backend.repository

import com.spproject.sp_backend.model.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID


//* DAO interface to User Entity
interface UsersRepository: CrudRepository<User, UUID> {
    fun findByUsername(username: String): User?
    fun findByKeyVal(keyVal: String): User?
}
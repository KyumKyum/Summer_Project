package com.spproject.sp_backend.test_db

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.repository.UsersRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.security.MessageDigest
import kotlin.IllegalStateException

//* Testing User Repository

@SpringBootTest
@Transactional //* Rollback to the base state after test
class UserRepositoryTest (
    @Autowired val usersRepository: UsersRepository
) {
    @Nested
    inner class CrudTest{
        @Test
        @DisplayName("Test User Creation")
        fun `create user`(){
            //given
            val newUser = UserDto(
                id = null, //* DB will generate it.
                username = "TEST BOT",
                ident = "Test",
                password = "Test",
                keyVal = "Test",
            ).toDomain();

            //when
            val saved = usersRepository.save(newUser);

            //then
            assertEquals(newUser.id, saved.id); //* Check generated value
            assertEquals(newUser.username, saved.username); //* Check Given Value
        }

        @Test
        @DisplayName("Test Default Find (findById)")
        fun `find user by id`(){
            //given
            val newUser = UserDto(
                id = null, //* DB will generate it.
                username = "TEST BOT",
                ident = "Test",
                password = "Test",
                keyVal = "Test",
            ).toDomain();

            usersRepository.save(newUser);

            //when
            val findUser = usersRepository.findById(newUser.id!!)
                .orElseThrow{ IllegalStateException("There is no such user") }

            //then
            assertEquals(newUser.id, findUser.id);
            assertEquals(newUser.username, findUser.username);
        }

        @Test
        @DisplayName("Test Defined Find (findByUsername)")
        fun `find user by username`(){
            //given
            val newUser = UserDto(
                id = null, //* DB will generate it.
                username = "Jay Lim",
                ident = "Test",
                password = "Test",
                keyVal = "Test",
            ).toDomain();

            usersRepository.save(newUser);

            //when
            val findUser = usersRepository.findByUsername(newUser.username)

            //then
            assertEquals(newUser.id, findUser?.id)
            assertEquals(newUser.username, findUser?.username)
            assertEquals(findUser?.username , "Jay Lim")
        }
    }
}
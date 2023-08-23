package com.spproject.sp_backend.test_utils

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.service.utils.PasswordUtils
import com.spproject.sp_backend.service.utils.SignInUtils
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@SpringBootTest
@Transactional
class SignInUtilsTest(
    @Autowired private val signInUtils: SignInUtils,
    @Autowired private val usersRepository: UsersRepository,
    @Autowired private val passwordUtils: PasswordUtils
) {

    @Test
    @DisplayName("Test checkValidSignIn")
    fun `check valid sign in`(){
        //given
        val ident:String = "Test"
        val password:String = "Test"

        //then
        assertTrue { signInUtils.checkValidSignIn(ident,password) }
        assertFalse { signInUtils.checkValidSignIn("", password) }
        assertFalse { signInUtils.checkValidSignIn(ident, "") }

    }

    @Test
    @DisplayName("Test searchUser")
    fun `check search user`(){
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATEST",
            ident = "IAMATEST",
            password = "IAMATEST",
            keyVal = "IAMATEST",
        )

        usersRepository.save(newUser.toDomain())

        //when
        val validId: String = "IAMATEST"
        val invalidId: String = "THISSHOULDBEWRONG"

        //then
        assertEquals(signInUtils.searchUser(validId), newUser)
        assertNull(signInUtils.searchUser(invalidId))

    }

    @Test
    @DisplayName("Test CheckRightPassword")
    fun `check right password`(){
        //given
        val password:String = "PASSWORD"
        val wrong:String = "WRONG"
        val encrypted: String = passwordUtils.getEncryptedPassword(password)

        //then
        assertTrue(signInUtils.checkRightPassword(encrypted, password))
        assertFalse { signInUtils.checkRightPassword(encrypted, wrong) }

    }
}
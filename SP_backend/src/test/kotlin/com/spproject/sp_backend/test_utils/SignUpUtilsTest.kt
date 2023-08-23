package com.spproject.sp_backend.test_utils

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.service.utils.SignUpUtils
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest
class SignUpUtilsTest (
    @Autowired private val signUpUtils:SignUpUtils
) {
    @Test
    @DisplayName("Test checkValidUserInfo")
    fun `check valid user`(){
        //given
        val validUser:UserDto = UserDto(null,"test","test", "test", null)
        val invalidUserNoName:UserDto = UserDto(null,null,"test", "test", null)
        val invalidUserNoId:UserDto = UserDto(null,"test",null, "test", null)
        val invalidUserNoPW:UserDto = UserDto(null,"test","test", null, null)

        //when

        //then
        assertTrue { signUpUtils.checkValidUserInfo(validUser) }
        assertFalse { signUpUtils.checkValidUserInfo(invalidUserNoId) }
        assertFalse { signUpUtils.checkValidUserInfo(invalidUserNoName) }
        assertFalse { signUpUtils.checkValidUserInfo(invalidUserNoPW) }
    }

    @Test
    @Transactional
    @DisplayName("Test createUser")
    fun `create USer`() {
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATTEST",
            ident = "IAMATTEST",
            password = "IAMATTEST",
            keyVal = "IAMATTEST",
        );

        //then
        assertTrue { signUpUtils.createUser(newUser) }
    }

    @Test
    @Transactional
    @DisplayName("Test checkUserDuplicates")
    fun `check duplicate user exists`(){
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATTEST",
            ident = "IAMATTEST",
            password = "IAMATTEST",
            keyVal = "IAMATTEST",
        );

        signUpUtils.createUser(newUser)

        //when
        val validUsername: String = "IAMATEST"
        val existingUsername: String = "IAMATTEST"

        val validIdent: String = "IAMATEST"
        val existingIdent: String = "IAMATTEST"

        //then
        assertTrue { signUpUtils.checkDuplicateUsernameExists(validUsername) }
        assertFalse { signUpUtils.checkDuplicateUsernameExists(existingUsername) }

        assertTrue { signUpUtils.checkDuplicateIdentExists(validIdent) }
        assertFalse { signUpUtils.checkDuplicateIdentExists(existingIdent)}
    }
}
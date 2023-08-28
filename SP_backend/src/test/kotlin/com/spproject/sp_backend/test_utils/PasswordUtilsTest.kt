package com.spproject.sp_backend.test_utils

import com.spproject.sp_backend.service.utils.security.PasswordUtils
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

//* Testing Password Utilities

@SpringBootTest
class PasswordUtilsTest (
    @Autowired val passwordUtils: PasswordUtils
) {
    @Test
    @DisplayName("Test Password Encryption")
    fun `encrypt password`(){
        //given
        val password:String = "Password";

        //when
        val encrypted = passwordUtils.getEncryptedPassword(password)

        //then
        println(encrypted)
        assertNotNull(encrypted)
    }

    @Test
    @DisplayName("Test Valid Encryption")
    fun `test encryption`(){
        //given
        val password:String = "12341234";
        val encrypted = passwordUtils.getEncryptedPassword(password)

        //when
        val right:String = passwordUtils.getEncryptedPassword("12341234")
        val wrong:String = passwordUtils.getEncryptedPassword("12341233")

        //then
        assertEquals(encrypted, right)
        assertNotEquals(encrypted, wrong)
    }
}
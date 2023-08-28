package com.spproject.sp_backend.test_service

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.global.response.ResponseService
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.signIn.SignInService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@Transactional
class SignInUtilsTest(
    @Autowired private val signInService: SignInService,
    @Autowired private val usersRepository: UsersRepository
) {

    @Test
    @DisplayName("TC1 - Valid Sign In")
    fun `valid sign in`() {
        //given
        val ident:String = "IAMATEST"
        val password:String = "IAMATEST"

        //when
        val res: SingleResponse<UserDto> = signInService.signIn(ident, password)

        //then
        assertTrue(res.ok)
        assertEquals(res.code,"SUCCESS")
        assertEquals(res.message, "Response Successful")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun `set up`(signInUtilsTest: SignInUtilsTest): Unit {
            val newUser = UserDto(
                id = null, //* DB will generate it.
                username = "IAMATEST",
                ident = "IAMATEST",
                password = "IAMATEST",
                keyVal = null,
            )

            signInUtilsTest.usersRepository.save(newUser.toDomain())
        }

        @JvmStatic
        @AfterAll
        fun remove(signInUtilsTest: SignInUtilsTest){
            signInUtilsTest.usersRepository.deleteByIdent("IAMATEST")
        }
    }
}
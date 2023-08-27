package com.spproject.sp_backend.test_service

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.signUp.SignUpService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
@Transactional
class SignUpServiceTest(
    @Autowired private val signUpService: SignUpService
) {

    @Test
    @DisplayName("TC1 - Valid User")
    fun `valid user sign up`(){
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATEST",
            ident = "IAMATEST",
            password = "IAMATEST",
            keyVal = null,
        )

        //when
        val res: SingleResponse<UserDto> = signUpService.signUp(newUser)

        //then
        assertEquals(res.ok, true)
        assertEquals(res.data, newUser)
        assertEquals(res.code,"SUCCESS")
        assertEquals(res.message, "Response Successful")
    }

    @Test
    @DisplayName("TC2 - Invalid User (invalid Input)")
    fun `invalid user sign up - invalid input`(){
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = null,
            ident = null,
            password = null,
            keyVal = null,
        )

        //when
        val res: SingleResponse<UserDto> = signUpService.signUp(newUser)

        //then
        assertEquals(res.ok, false)
        assertEquals(res.data, newUser)
        assertEquals(res.code,"SIGNUP-FAILED")
        assertEquals(res.message, "Not valid user input")
    }

    @Test
    @DisplayName("TC3 - Invalid User (Duplicated User)")
    fun `invalid user sign up - Duplicated User`(){
        //given
        val newUser = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATEST",
            ident = "IAMATEST",
            password = "IAMATEST",
            keyVal = null,
        )
        signUpService.signUp(newUser)

        //when

        val duplicatedID = UserDto(
            id = null, //* DB will generate it.
            username = "a",
            ident = "IAMATEST",
            password = "a",
            keyVal = null,
        )

        val duplicatedUsername = UserDto(
            id = null, //* DB will generate it.
            username = "IAMATEST",
            ident = "a",
            password = "a",
            keyVal = null,
        )

        val dupIdRes: SingleResponse<UserDto> = signUpService.signUp(duplicatedID)
        val dupNameRes: SingleResponse<UserDto> = signUpService.signUp(duplicatedUsername)


        //then
        assertEquals(dupIdRes.ok, false)
        assertEquals(dupIdRes.data, duplicatedID)
        assertEquals(dupIdRes.code,"SIGNUP-FAILED")
        assertEquals(dupIdRes.message, "Duplicate ID")

        assertEquals(dupNameRes.ok, false)
        assertEquals(dupNameRes.data, duplicatedUsername)
        assertEquals(dupNameRes.code,"SIGNUP-FAILED")
        assertEquals(dupNameRes.message, "Duplicate Username")
    }

}
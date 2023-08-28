package com.spproject.sp_backend.test_controller

import com.spproject.sp_backend.controller.MemberController
import com.spproject.sp_backend.dto.SignInDto
import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.response.SingleResponse
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@SpringBootTest
@Transactional
class MemberControllerTest(
    @Autowired private val memberController: MemberController
) {
    @Test
    @DisplayName("Test - Sign Up")
    fun `sign Up test`(){
        //given
        val user:UserDto = UserDto(
            null,
            "dsfdsfsd",
            "sdfsdf",
            "tesdfdsfsdst",
            null
        );

        //when
        val res:SingleResponse<UserDto> = memberController.handleSignUp(user);

        //then
        println(res.data.id)
        println(res.message)

        assertTrue(res.ok)
        assertEquals(res.data.username, user.username);
        assertNotNull(res.data.id);
        assertNotNull(res.data.keyVal);
    }

    @Test
    @DisplayName("Test - Sign In")
    fun `sign in test`(){
        //given
        val user:UserDto = UserDto(
            null,
            "dsfdsfsd",
            "sdfsdf",
            "tesdfdsfsdst",
            null
        );
        memberController.handleSignUp(user);

        val signIn: SignInDto = SignInDto(
            "sdfsdf",
            "tesdfdsfsdst"
        )

        val wrong: SignInDto = SignInDto(
            "Invalid",
            "Invalid"
        )

        //when
        val correct:SingleResponse<UserDto> = memberController.handleSignIn(signIn);
        val error:SingleResponse<UserDto> = memberController.handleSignIn(wrong)

        println(correct.data.id)

        assertTrue(correct.ok)
        assertFalse { error.ok }
    }
}
package com.spproject.sp_backend.test_controller

import com.spproject.sp_backend.controller.GenerateController
import com.spproject.sp_backend.controller.MemberController
import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.generate.data.GenerateRequest
import com.spproject.sp_backend.service.generate.data.GeneratedAvatar
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest
@Transactional
class GenerateControllerTest(
    @Autowired private val generateController: GenerateController,
    @Autowired private val memberController: MemberController
) {
    @Test
    @DisplayName("Test Avatar Generation")
    fun `test avatar gen`(){
        //given
        val user: UserDto = UserDto(
            null,
            "dsfdsfsd",
            "sdfsdf",
            "tesdfdsfsdst",
            null
        );
        val res: SingleResponse<UserDto> = memberController.handleSignUp(user);

        val genReq:GenerateRequest = GenerateRequest(
            res.data.id!!,
            res.data.username!!,
            240,
            res.data.keyVal!!
        )

        //when
        val result:SingleResponse<GeneratedAvatar?> = generateController.handleGeneration(genReq)

        assertTrue { result.ok }
        assertNotNull(result.data)
        assertTrue { result.data!!.ok }
        assertNotNull(result.data!!.avatarUrl);
        println(result.data!!.avatarUrl)
    }
}
package com.spproject.sp_backend.test_service

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.request.SingleRequest
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.generate.GenerateService
import com.spproject.sp_backend.service.generate.data.GenerateRequest
import com.spproject.sp_backend.service.generate.data.GeneratedAvatar
import com.spproject.sp_backend.service.signIn.utils.SignInUtils
import com.spproject.sp_backend.service.signUp.utils.SignUpUtils
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest
@Transactional
class GenerateServiceTest(
    @Autowired private val generateService: GenerateService,
    @Autowired private val signUpUtils: SignUpUtils
) {
    @Test
    @DisplayName("Test Avatar Generation")
    fun `generate avatar`(){
        //given
        val payload: GenerateRequest = GenerateRequest(
            UUID.fromString("02c5cc98-368e-11ee-be56-0242ac120002"),
            "Test",
            240,
            "dfsdfdsf2334"
        )
        val req: SingleRequest<GenerateRequest> = SingleRequest(payload)

        //when
        val res: SingleResponse<GeneratedAvatar?> = generateService.generateAvatarRequest(req);

        //then
        assertTrue { res.ok }
        assertNotNull(res.data)

        val recvPayload:GeneratedAvatar = res.data!!

        assertNotNull(recvPayload)
        //assertTrue { recvPayload.ok }
        println(recvPayload.avatarUrl)
        println(recvPayload.message)
    }
}
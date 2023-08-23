package com.spproject.sp_backend.test_utils

import com.spproject.sp_backend.service.utils.jwt.JWTUtils
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
class JWTUtilTest(
    @Autowired private val jwtUtils: JWTUtils
) {
    @Test
    @DisplayName("TC1 - Generate JWT")
    fun `generate JWT`(){
        //given
        val userId:String = UUID.randomUUID().toString()
        //when
        val token: String = jwtUtils.generateJWT(userId);
        val extracted: String? = jwtUtils.getUserId(token)
        //then
        println(token)
        assertNotNull(token)
        assertNotNull(extracted)
        assertEquals(userId, extracted)
    }

    @Test
    @DisplayName("TC2 - Malformed JWT")
    fun `Error - Not valid jwt`(){
        //given
        val wrong: String = "RANDOM"

        //when & given
        assertThrows<MalformedJwtException> { jwtUtils.getUserId(wrong) }
    }

    @Test
    @DisplayName("TC3 - Expired JWT")
    fun `Error - Expired jwt`(){
        //given
        val userId:String = UUID.randomUUID().toString()
        val expired: Long = 1L;

        //when
        val token:String = jwtUtils.generateJWT(userId,expired)

        Thread.sleep(10L)

        //then
        assertThrows<ExpiredJwtException> { jwtUtils.getUserId(token) }
    }
}
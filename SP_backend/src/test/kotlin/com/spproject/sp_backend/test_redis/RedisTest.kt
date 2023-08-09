package com.spproject.sp_backend.test_redis

import com.spproject.sp_backend.model.redis.ValidationKey
import com.spproject.sp_backend.repository.redis.ValidationKeyRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.util.UUID
import kotlin.test.AfterTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@Transactional
class RedisTest(
    @Autowired val validationKeyRepository: ValidationKeyRepository
){
    @Test
    @DisplayName("Test Redis set")
    fun `redis - set value`(){
        //given
        val testKey:String = "testtest"
        val testId:UUID = UUID.randomUUID()

        val validationKey: ValidationKey = ValidationKey(testKey, testId);

        //when
        val res: ValidationKey = validationKeyRepository.save(validationKey)

        //then
        assertEquals(testKey, res.key)
        assertEquals(testId, res.userId)
    }

    @Test
    @DisplayName("Test Redis find by key")
    fun `redis - find by key`(){
        //given
        val testKey:String = "testtest"
        val testId:UUID = UUID.randomUUID()

        val validationKey: ValidationKey = ValidationKey(testKey, testId);

        //when
        validationKeyRepository.save(validationKey)
        val ret:ValidationKey? = validationKeyRepository.findByKey(testKey);

        //then
        assertNotNull(ret)
        assertEquals(ret.key, testKey)
        assertEquals(ret.userId, testId)
    }

}
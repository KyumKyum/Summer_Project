package com.spproject.sp_backend.test_redis

import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
@Transactional
class RedisTest(
    private val redisTemplate: RedisTemplate<String, Any>,
){

}
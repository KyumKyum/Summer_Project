package com.spproject.sp_backend.model.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.UUID

@RedisHash(value = "ValidationToken", timeToLive = 100)
class ValidationKey (
    @Id
    @Indexed
    val key: String,

    val userId: UUID,
)
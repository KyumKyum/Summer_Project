package com.spproject.sp_backend.repository.redis

import com.spproject.sp_backend.model.redis.ValidationKey
import org.springframework.data.repository.CrudRepository

interface ValidationKeyRepository: CrudRepository<ValidationKey, String> {
    fun findByKey(key: String?):ValidationKey?
}
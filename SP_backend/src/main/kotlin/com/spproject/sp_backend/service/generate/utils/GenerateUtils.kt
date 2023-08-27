package com.spproject.sp_backend.service.generate.utils

import org.springframework.stereotype.Component

@Component
class GenerateUtils<T> {
    fun checkValidPayload(payload: T): Boolean {
        return payload != null;
    }
}
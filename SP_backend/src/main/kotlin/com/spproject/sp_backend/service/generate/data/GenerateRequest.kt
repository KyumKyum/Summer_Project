package com.spproject.sp_backend.service.generate.data

import java.util.UUID

class GenerateRequest(
    val userId: UUID,
    val name: String,
    val size: Int,
    val keyVal: String
)
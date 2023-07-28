package com.spproject.sp_backend.dto

interface Dto<D> {
    fun toDomain(): D
}
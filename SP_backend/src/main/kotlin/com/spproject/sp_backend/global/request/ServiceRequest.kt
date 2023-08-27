package com.spproject.sp_backend.global.request

interface ServiceRequest<T> {
    val payload: T
}
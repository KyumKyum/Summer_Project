package com.spproject.sp_backend.global.request

class SingleRequest<T>(
    override val payload: T
) : ServiceRequest<T>
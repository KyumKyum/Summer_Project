package com.spproject.sp_backend.service.response

interface ServiceResponse {
    val ok: Boolean
    val code: String
    val message: String

    fun setVal(ok:Boolean, code:String, message:String)
}
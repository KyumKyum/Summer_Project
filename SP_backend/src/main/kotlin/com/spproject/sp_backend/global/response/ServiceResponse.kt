package com.spproject.sp_backend.global.response

interface ServiceResponse {
    val ok: Boolean
    val code: String
    val message: String

    fun setVal(ok:Boolean, code:String, message:String)
}
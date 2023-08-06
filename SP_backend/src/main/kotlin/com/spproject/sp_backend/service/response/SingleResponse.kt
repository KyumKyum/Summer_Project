package com.spproject.sp_backend.service.response

class SingleResponse<T> (
    val data: T
) : ServiceResponse {

    //TODO - Must refactor this code!!!
    override var ok: Boolean = true
    override var message: String = ""
    override var code: String = ""

    override fun setVal(ok: Boolean, code: String, message: String) {
        this.ok = ok
        this.code = code
        this.message = message
    }
}
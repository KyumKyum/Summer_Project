package com.spproject.sp_backend.global.response

open class GenerateResponse {
    protected fun setSuccessfulResponse(response: ServiceResponse) {
        response.setVal(
            ok = true,
            code = "SUCCESS",
            message = "Response Successful"
        )
    }

    protected fun setFailureResponse(
        response: ServiceResponse,
        code: String,
        message: String
    ) {
        response.setVal(
            ok = false,
            code,
            message
        )
    }
}
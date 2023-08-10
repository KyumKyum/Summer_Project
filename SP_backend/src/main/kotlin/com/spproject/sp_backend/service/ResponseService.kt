package com.spproject.sp_backend.service

import com.spproject.sp_backend.service.response.ServiceResponse
import com.spproject.sp_backend.service.response.SingleResponse
import org.springframework.stereotype.Service

@Service
class ResponseService<T> {
    fun getSingleSuccessfulResponse(data:T): SingleResponse<T>{
        val singleResponse = SingleResponse<T>(data)
        setSuccessfulResponse(singleResponse)

        return singleResponse
    }

    fun setSuccessfulResponse(response: ServiceResponse){
        response.setVal(
            ok = true,
            code = "SUCCESS",
            message = "response successful"
        )

    }
}
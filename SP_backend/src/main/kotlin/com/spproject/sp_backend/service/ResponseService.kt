package com.spproject.sp_backend.service

import com.spproject.sp_backend.service.response.GenerateResponse
import com.spproject.sp_backend.service.response.ServiceResponse
import com.spproject.sp_backend.service.response.SingleResponse
import org.springframework.stereotype.Service

@Service
class ResponseService<T> : GenerateResponse() {
    fun getSingleSuccessfulResponse(data:T): SingleResponse<T>{
        val singleSuccessfulResponse = SingleResponse<T>(data)
        setSuccessfulResponse(singleSuccessfulResponse)

        return singleSuccessfulResponse
    }

    fun getSingleFailureResponse(data:T, code: String, message:String): SingleResponse<T> {
        val singleFailureResponse = SingleResponse<T>(data)
        setFailureResponse(singleFailureResponse, code, message)

        return singleFailureResponse;
    }
}
package com.spproject.sp_backend.service.generate

import com.fasterxml.jackson.databind.ObjectMapper
import com.spproject.sp_backend.global.request.SingleRequest
import com.spproject.sp_backend.global.response.ResponseService
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.repository.redis.ValidationKeyRepository
import com.spproject.sp_backend.service.ValidationService
import com.spproject.sp_backend.service.generate.data.GenerateRequest
import com.spproject.sp_backend.service.generate.data.GeneratedAvatar
import com.spproject.sp_backend.service.generate.utils.GenerateUtils
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.springframework.stereotype.Service

@Service
class GenerateService(
    private val validationKeyRepository: ValidationKeyRepository,
    private val usersRepository: UsersRepository,
    private val objectMapper: ObjectMapper,
    private val okHttpClient: OkHttpClient
){

    fun generateAvatarRequest(req: SingleRequest<GenerateRequest>): SingleResponse<GeneratedAvatar?> {
        val generateUtils: GenerateUtils<GenerateRequest> = GenerateUtils() //* Utils for generation Request
        val responseService: ResponseService<GeneratedAvatar?> = ResponseService()
        val validationService: ValidationService = ValidationService(validationKeyRepository, usersRepository)

        val payload: GenerateRequest = req.payload;

        try{
            if(!generateUtils.checkValidPayload(payload)) throw Error("NotValidPayload");

            if(payload.userId.toString().isEmpty()) throw Error("NotValidKey");

            validationService.saveValidationKey(payload.userId)

            //* Send Request
            //TODO
            val requestBody: String = objectMapper.writeValueAsString(payload)

            val httpResponse: Response = try{
                okHttpClient.newCall(
                    Request.Builder()
                        .url("http://localhost:5001/generate/")
                        .post(requestBody.toRequestBody("application/json; charset=utf-8".toMediaType()))
                        .build()
                ).execute()
            }catch (e: Error){
                throw Error("RequestError: ${e.message}}")
            }

            val response:GeneratedAvatar = objectMapper.readValue(httpResponse.body?.string(), GeneratedAvatar::class.java)
            //* Not .toString method: Ref: https://stackoverflow.com/questions/72454745/caused-by-com-fasterxml-jackson-core-jsonparseexception-unrecognized-token-ok

            return responseService.getSingleSuccessfulResponse(response)

        }catch (e: Error){
            return when(e.message){
                "NotValidPayload" -> responseService.getSingleFailureResponse(null, "GEN-FAILED", "Not Valid Payload")
                "NotValidKey" -> responseService.getSingleFailureResponse(null, "GEN-FAILED", "Not Valid Key")
                else -> responseService.getSingleFailureResponse(null, "GEN-FAILED", "Fatal Error!! ${e.message}")
            }
        }
    }
}
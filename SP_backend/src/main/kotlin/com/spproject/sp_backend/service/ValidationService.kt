package com.spproject.sp_backend.service

import com.spproject.sp_backend.model.User
import com.spproject.sp_backend.model.redis.ValidationKey
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.repository.redis.ValidationKeyRepository
import com.spproject.sp_backend.global.response.ResponseService
import com.spproject.sp_backend.global.response.SingleResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ValidationService(
    private val validationKeyRepository: ValidationKeyRepository,
    private val usersRepository: UsersRepository
) {
    //* Save validation key - this key will be checked in microservice.
    fun saveValidationKey(userId:UUID): SingleResponse<ValidationKey> {
        //* Check if user exists.
        val user:User = usersRepository.findById(userId).get()
        val responseService: ResponseService<ValidationKey> = ResponseService()

        //* TODO: Add Error class. throw error by using elvis operator
        //* TODO: Finish logic

        val validationKey:ValidationKey = ValidationKey(user.keyVal, user.id!!)

        validationKeyRepository.save(validationKey)

        return responseService.getSingleSuccessfulResponse(validationKey)
    }
}
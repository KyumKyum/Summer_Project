package com.spproject.sp_backend.service.signUp

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.response.ResponseService
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.utils.security.PasswordUtils
import com.spproject.sp_backend.service.signUp.utils.SignUpUtils
import org.springframework.stereotype.Service
import java.util.UUID

//* Handles Sign Up

@Service
class SignUpService(
    private val passwordUtils: PasswordUtils,
    private val responseService: ResponseService<UserDto>,
    private val signUpUtils: SignUpUtils
) {
    //* Proceed sign up
    fun signUp(user: UserDto): SingleResponse<UserDto> {
        try {
            //* Check if current data is valid.
            if(!signUpUtils.checkValidUserInfo(user)){
                //TODO need to create error class
                throw Error("NotValidUser")
            }

            if(!signUpUtils.checkDuplicateIdentExists(user.ident!!)){
                //TODO need to create error class
                throw Error("DuplicateID")
            }

            if(!signUpUtils.checkDuplicateUsernameExists(user.username!!)){
                //TODO need to create error class
                throw Error("DuplicateUsername")
            }

            //* Valid User Information
            val encryptedPW = passwordUtils.getEncryptedPassword(user.password!!)
            val key: String = UUID.randomUUID().toString()

            val newUser: UserDto = signUpUtils.createUser(
                UserDto(
                    null,
                    user.username!!,
                    user.ident!!,
                    encryptedPW,
                    key)
            )
                ?: //TODO need to create error class
                throw Error("SaveError")


            return responseService.getSingleSuccessfulResponse(newUser);

            //* User successfully created!
        }catch (error:Error){
            return when (error.message) {
                "NotValidUser" -> responseService.getSingleFailureResponse(user,"SIGNUP-FAILED", "Not valid user input")
                "DuplicateUsername" -> responseService.getSingleFailureResponse(user,"SIGNUP-FAILED", "Duplicate Username")
                "DuplicateID" -> responseService.getSingleFailureResponse(user,"SIGNUP-FAILED", "Duplicate ID")
                "SaveError" -> responseService.getSingleFailureResponse(user,"SIGNUP-FAILED", "Save Error!")
                else -> responseService.getSingleFailureResponse(user,"SIGNUP-FAILED", "Fatal Error!")
            }
        }
    }
}
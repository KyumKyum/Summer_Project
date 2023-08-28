package com.spproject.sp_backend.service.signIn

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.response.ResponseService
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.utils.security.PasswordUtils
import com.spproject.sp_backend.service.signIn.utils.SignInUtils
import org.springframework.stereotype.Service

//* Handles Sign In (Log in)


@Service
class SignInService(
    private val passwordUtils: PasswordUtils,
    private val responseService: ResponseService<UserDto>,
    private val signInUtils: SignInUtils
) {

    //* Proceed Sign In
    //* Param: id and password
    fun signIn(
        ident: String,
        password: String
    ): SingleResponse<UserDto> {

        lateinit var user: UserDto

        try {
            //* Check Valid Input
            if(!signInUtils.checkValidSignIn(ident, password)){
                throw Error("NotValidInput")
            }

            //* Get User Information
            user = signInUtils.searchUser(ident) ?: throw Error("NoSuchUser")

            //* Check If Valid Password
            if(!signInUtils.checkRightPassword(user.password!!, password)){
                throw Error("NotCorrectPassword")
            }

        }catch (error: Error){
            user = UserDto(
                null,
                null,
                null,
                null,
                null
            )
            return when(error.message){
                "NotValidInput" -> responseService.getSingleFailureResponse(user, "SIGNIN-FAILED", "Not valid sign up input")
                "NoSuchUser" -> responseService.getSingleFailureResponse(user, "SIGNIN-FAILED", "No Such User")
                "NotCorrectPassword" -> responseService.getSingleFailureResponse(user, "SIGNIN-FAILED", "Not correct password")
                else -> responseService.getSingleFailureResponse(user,"SIGNIN-FAILED", "Fatal Error!")
            }
        }

        return responseService.getSingleSuccessfulResponse(user)
    }

}
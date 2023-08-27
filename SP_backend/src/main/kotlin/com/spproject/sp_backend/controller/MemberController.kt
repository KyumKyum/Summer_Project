package com.spproject.sp_backend.controller

import com.spproject.sp_backend.dto.SignInDto
import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.global.request.SingleRequest
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.model.User
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.service.generate.data.GenerateRequest
import com.spproject.sp_backend.service.signIn.SignInService
import com.spproject.sp_backend.service.signUp.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/members")
class MemberController(
    @Autowired private val signUpService: SignUpService,
    @Autowired private val signInService: SignInService,
) {
    @PostMapping("/signUp")
    fun handleSignUp(@RequestBody userDto: UserDto): SingleResponse<UserDto>{
        return signUpService.signUp(userDto)
    }


    @PostMapping("/signIn")
    fun handleSignIn(@RequestBody signInDto: SignInDto): SingleResponse<UserDto>{
        return signInService.signIn(signInDto.id, signInDto.pw)
    }
}
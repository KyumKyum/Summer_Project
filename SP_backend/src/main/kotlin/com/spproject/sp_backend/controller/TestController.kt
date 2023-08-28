package com.spproject.sp_backend.controller

import com.spproject.sp_backend.global.response.SingleResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/test")
class TestController {
    @PostMapping()
    fun testEndpoint():SingleResponse<String>{
        val recv:String = "Successful!"
        return SingleResponse(recv);
    }
}
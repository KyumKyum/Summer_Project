package com.spproject.sp_backend.controller

import com.spproject.sp_backend.global.request.SingleRequest
import com.spproject.sp_backend.global.response.SingleResponse
import com.spproject.sp_backend.service.generate.GenerateService
import com.spproject.sp_backend.service.generate.data.GenerateRequest
import com.spproject.sp_backend.service.generate.data.GeneratedAvatar
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/generate")
class GenerateController(
    @Autowired private val generateService: GenerateService,
){
    @PostMapping()
    fun handleGeneration(@RequestBody generateRequest: GenerateRequest): SingleResponse<GeneratedAvatar?>{
        val req: SingleRequest<GenerateRequest> = SingleRequest<GenerateRequest>(generateRequest)
        return generateService.generateAvatarRequest(req)
    }
}

package com.spproject.sp_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
class SpBackendApplication {
    @GetMapping("/")
    fun blog(model:Model): String {
        return "Test"
    }
}

fun main(args: Array<String>) {
    runApplication<SpBackendApplication>(*args)
}

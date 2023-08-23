package com.spproject.sp_backend.config.security

import com.spproject.sp_backend.service.utils.jwt.JWTUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtils: JWTUtils
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
         //* TODO


        return http.build()
    }
}
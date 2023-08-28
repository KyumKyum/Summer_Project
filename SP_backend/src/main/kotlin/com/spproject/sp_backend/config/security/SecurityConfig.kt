package com.spproject.sp_backend.config.security

import com.spproject.sp_backend.service.utils.jwt.JWTUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtils: JWTUtils
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
         //* CORS
        //* TODO
        http.cors().configurationSource(corsConfigSource())
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.headers().frameOptions().disable()
        http.headers().xssProtection().disable()

        return http.build()
    }
}

@Bean
fun corsConfigSource(): CorsConfigurationSource {
    val configuration = CorsConfiguration();

    configuration.allowedOrigins = listOf("http://localhost:8080", "http://localhost:3000")
    configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
    configuration.allowedHeaders = listOf("Origin", "X-Requested-With", "Content-Type", "Authorization", "Oauth-Token")
    configuration.maxAge = 3000L
    configuration.allowCredentials = true;

    val source = UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
}
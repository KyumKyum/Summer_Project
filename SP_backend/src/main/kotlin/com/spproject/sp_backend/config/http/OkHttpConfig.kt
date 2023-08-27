package com.spproject.sp_backend.config.http

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class OkHttpConfig {

    @Bean("okHttpClient")
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder().apply {
                connectTimeout(10, TimeUnit.SECONDS) //* Connect to server 10 sec
                writeTimeout(10, TimeUnit.SECONDS) //* Serve request for 10 sec
                readTimeout(10, TimeUnit.SECONDS) //* Wait server response for 10 sec.
            }.build()
    }
}
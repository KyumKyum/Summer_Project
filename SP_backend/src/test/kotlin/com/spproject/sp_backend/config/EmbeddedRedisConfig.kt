package com.spproject.sp_backend.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import redis.embedded.RedisServer

@TestConfiguration
class EmbeddedRedisConfig(
    @Value("\${spring.redis.port}") val port: Int,
    @Value("\${spring.redis.host}") val host: String
) {
    lateinit var redisServer: RedisServer;

    @Bean
    fun redisConnectionFactory() : RedisConnectionFactory {
        return LettuceConnectionFactory(host, port);
    }

    @PostConstruct
    fun startRedis() {
        redisServer = RedisServer(port);
        redisServer.start()
    }

    @PreDestroy
    fun terminateRedis() {
        redisServer.stop()
    }
}
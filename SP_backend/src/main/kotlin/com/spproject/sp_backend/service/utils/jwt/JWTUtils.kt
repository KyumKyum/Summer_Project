package com.spproject.sp_backend.service.utils.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date


//* Generate JWT, Query & Extract user information
@Component
class JWTUtils {

    //* Need to hide in real deployment. Write in plain text as this is a toy project.
    private val signKey: String = "rkGU45258GGhiolLO2465TFY5345kGU45258GGhiolLO2465TFY5345"
    private val validTime: Long = 60L * 60L * 1000L //* 1 hr
    private lateinit var encodedSecretKey: Key //* Need to encode in byte array -

    @PostConstruct
    private fun init(){
        //* Init code
        encodedSecretKey = Keys.hmacShaKeyFor(signKey.toByteArray(StandardCharsets.UTF_8))
    }

    //* Generate JWT
    fun generateJWT(
        userId: String,
        expiredAfter: Long = validTime
    ): String {
        val now: Date = Date()
        val expired: Date = Date(now.time + expiredAfter)
        val claims: Claims = Jwts.claims()
        //* Required Information
        claims["userId"] = userId //* Key - Value Pair

        return Jwts.builder()
            .setHeaderParam("typ", "param")
            .setClaims(claims) //* Save Info
            .setSubject(userId)  //* Save information in payload
            .setIssuedAt(now)  //* Save Issued Time
            .setExpiration(expired) //* Save Expired Time
            .signWith(encodedSecretKey, SignatureAlgorithm.HS256) //* Set secret value for signature
            .compact()
    }

    //* Extract
    fun getUserId(jwt: String):String? {
        val claims =  Jwts.parserBuilder()
            .setSigningKey(encodedSecretKey)
            .build()
            .parseClaimsJws(jwt)

        if (claims.body.expiration.after(Date())) {
            return claims.body.subject
        }

        return null //* ERROR! Corrupted JWT Token
    }

    //* Get jwt from request
    fun getTokenFromHTTPRequest(req: HttpServletRequest): String? {
        return req.getHeader("Authorization")
    }

}
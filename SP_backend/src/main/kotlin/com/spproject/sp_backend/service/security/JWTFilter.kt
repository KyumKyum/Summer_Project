package com.spproject.sp_backend.service.security

import com.spproject.sp_backend.dto.UserDto
import com.spproject.sp_backend.repository.UsersRepository
import com.spproject.sp_backend.service.utils.jwt.JWTUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.UUID


//* Servlet Filter
//* Get servlet filter in the very first time.
//* Request comes -> chain?.doFilter(req,res) -> After

class JWTFilter(
    private val jwtUtils: JWTUtils,
    private val userDto: UserDto,
    private val usersRepository: UsersRepository
): GenericFilterBean(){

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        //* Get jwt from request
        val token: String? = jwtUtils.getTokenFromHTTPRequest(request as HttpServletRequest)

        if(token != null){
            val userId: String = jwtUtils.getUserId(token) ?: throw Error("Not Valid JWT")
            //* Get User Info.
            val curUserInfo:UserDto = userDto.toDto(usersRepository.findById(UUID.fromString(userId)).get()) ?: throw Error("Not Valid User")

            //* Save in security Context Holder
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(curUserInfo, "")
        }

        chain?.doFilter(request,response)

    }
}
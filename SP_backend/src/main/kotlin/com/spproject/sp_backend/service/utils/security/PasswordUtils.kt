package com.spproject.sp_backend.service.utils.security

import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class PasswordUtils {
    fun getEncryptedPassword(pw: String): String{
        val b: ByteArray = pw.toByteArray()
        val md: MessageDigest = MessageDigest.getInstance("SHA-256")
        val digest: ByteArray = md.digest(b)

        return digest.fold("") { //* Convert Byte to string, start on empty string.
            str: String, it: Byte -> str + "%02x".format(it)
        }
    }
}
package com.example.jwt_demo.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    private val secretKey = "mySecretKey"
    private val algorithm = Algorithm.HMAC256(secretKey)

    fun createToken(username: String): String {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 horas de expiración
            .sign(algorithm)
    }

    fun extractUsername(token: String): String {
        return getDecodedJWT(token).subject
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun getDecodedJWT(token: String): DecodedJWT {
        return JWT.require(algorithm).build().verify(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getDecodedJWT(token).expiresAt
        return expiration.before(Date())
    }

    fun getAuthenticationToken(token: String, userDetails: UserDetails): Authentication {
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }
}

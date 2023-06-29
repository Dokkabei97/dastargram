package com.d1t.dastargram.global.common.utils

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class AccessTokenProvider(
        @Value("\${jwt.access-token.secret-key}")
        private val accessTokenSecretKey: String,

        @Value("\${jwt.access-token.expire-time}")
        private val accessTokenExpireTime: Long
) : JwtTokenProvider {
    private val key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(accessTokenSecretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    override fun generateToken(userId: Long): String {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(Date(System.currentTimeMillis() + accessTokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    override fun validateToken(token: String): Boolean {
        return runCatching {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        }.getOrElse { e ->
            when (e) {
                is MalformedJwtException, is SignatureException -> false
                is ExpiredJwtException -> true
                else -> throw e
            }
        }
    }
}
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

sealed class JwtTokenProvider(
        secretKey: String,
        private val expireTime: Long
) {
    private val key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(userId: Long): String {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(Date(System.currentTimeMillis() + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    // TODO: 재발급 로직을 위해 검증 로직 변경 필요
    fun validateToken(token: String): Boolean {
        return runCatching {
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        }.getOrElse { e ->
            when (e) {
                is MalformedJwtException, is SignatureException, is ExpiredJwtException -> false
                else -> throw e
            }
        }
    }

    fun refreshToken(token: String): String {
        check(validateToken(token)) { "Invalid token." }
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        return generateToken(claims.subject.toLong())
    }
}

@Component
class AccessTokenProvider(
        @Value("\${jwt.access-token.secret-key}")
        accessTokenSecretKey: String,

        @Value("\${jwt.access-token.expire-time}")
        accessTokenExpireTime: Long
) : JwtTokenProvider(accessTokenSecretKey, accessTokenExpireTime)

@Component
class RefreshTokenProvider(
        @Value("\${jwt.refresh-token.secret-key}")
        refreshTokenSecretKey: String,

        @Value("\${jwt.refresh-token.expire-time}")
        refreshTokenExpireTime: Long
) : JwtTokenProvider(refreshTokenSecretKey, refreshTokenExpireTime)

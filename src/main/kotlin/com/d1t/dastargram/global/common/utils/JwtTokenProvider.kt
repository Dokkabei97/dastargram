package com.d1t.dastargram.global.common.utils

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

sealed class JwtTokenProvider(
        secretKey: String,
        private val expireTime: Long
) {
    private val key: Key
    private val log = LoggerFactory.getLogger(this::class.java)

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(email: String, authorities: String): String {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(SUBJECT)
                .setExpiration(Date(System.currentTimeMillis() + expireTime))
                .claim(URL, true)
                .claim(EMAIL_KEY, email)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    fun generateToken(): String {
        return Jwts.builder()
                .setExpiration(Date(System.currentTimeMillis() + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    fun validateToken(token: String): Boolean {
        return runCatching {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        }.getOrElse { e ->
            when (e) {
                is MalformedJwtException, is SignatureException, is ExpiredJwtException, is UnsupportedJwtException -> {
                    log.error("AccessToken 검증 실패", e)
                    false
                }

                else -> {
                    throw e
                }
            }
        }
    }

    fun validateTokenForReissue(token: String): Boolean {
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

    fun reissueToken(accessToken: String, refreshToken: String): Pair<String, String> {
        check(validateTokenForReissue(accessToken)) { "AccessToken 검증 실패" }
        check(validateToken(refreshToken)) { "RefreshToken 검증 실패" }
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).body
        val email = claims[EMAIL_KEY] as String
        val authorities = claims[AUTHORITIES_KEY] as String
        return generateToken(email, authorities) to generateToken()
    }

    companion object {
        private const val URL: String = "http://localhost:8080"
        private const val SUBJECT: String = "AccessToken"
        private const val EMAIL_KEY: String = "email"
        private const val AUTHORITIES_KEY: String = "role"
    }
}

@Component
class AccessTokenProvider(
        @Value("\${jwt.access-token.secret-key}")
        private val accessTokenSecretKey: String,

        @Value("\${jwt.access-token.expire-time}")
        private val accessTokenExpireTime: Long
): JwtTokenProvider(accessTokenSecretKey, accessTokenExpireTime)

@Component
class RefreshTokenProvider(
        @Value("\${jwt.refresh-token.secret-key}")
        private val refreshTokenSecretKey: String,

        @Value("\${jwt.refresh-token.expire-time}")
        private val refreshTokenExpireTime: Long
): JwtTokenProvider(refreshTokenSecretKey, refreshTokenExpireTime)

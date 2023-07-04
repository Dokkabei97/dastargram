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

    /**
     * open하고 override 안하는 이유는
     * accessToken은 email과 authorities를 claim에 넣어야 하고
     * refreshToken은 claim에 아무것도 넣지 않아야 하기 때문
     * 그래서 check(this is AccessTokenProvider)로 AccessTokenProvider가 아닐 경우 에러를 발생시킴으로
     * RefreshTokenProvider가 해당 generateToken을 호출하지 못하도록 함
     */
    fun generateToken(email: String, authorities: String): String {
        check(this is AccessTokenProvider) { "AccessTokenProvider가 아닙니다."}
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(ACCESS_TOKEN_SUBJECT)
                .setExpiration(Date(System.currentTimeMillis() + expireTime))
                .claim(URL, true)
                .claim(EMAIL_KEY, email)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    fun generateToken(): String {
        check(this is RefreshTokenProvider) { "RefreshTokenProvider가 아닙니다."}
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(REFRESH_TOKEN_SUBJECT)
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
                    log.error("Token 검증 실패", e)
                    false
                }
                else -> {
                    throw e
                }
            }
        }
    }

    /**
     * AccessToken 재발급 검증 코드
     * AccessToken이 만료되었을 경우 true를 반환
     * AccessToken이 만료가 안되었거나 검증에 실패했을 경우 false를 반환
     */
    fun validateTokenForReissue(token: String): Boolean {
        return runCatching {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            false
        }.getOrElse { e ->
            when (e) {
                is ExpiredJwtException -> true
                is MalformedJwtException, is SignatureException -> {
                    log.error("AccessToken 검증 실패", e)
                    false
                }
                else -> throw e
            }
        }
    }

    /**
     * 토큰 재발급 코드
     * AccessToken이 만료되었을 경우 AccessToken과 RefreshToken을 재발급
     * AccessToken이 만료가 안되었거나 검증에 실패했을 경우 에러 발생
     * RefreshToken이 만료되었거나 검증에 실패했을 경우 에러 발생
     */
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
        private const val ACCESS_TOKEN_SUBJECT: String = "AccessToken"
        private const val REFRESH_TOKEN_SUBJECT: String = "RefreshToken"
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

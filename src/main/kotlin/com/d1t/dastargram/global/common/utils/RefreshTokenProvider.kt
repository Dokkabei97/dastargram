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
class RefreshTokenProvider(
        @Value("\${jwt.refresh-token.secret-key}")
        private val refreshTokenSecretKey: String,

        @Value("\${jwt.refresh-token.expire-time}")
        private val refreshTokenExpireTime: Long,

        private val accessTokenProvider: AccessTokenProvider
) : JwtTokenProvider {
    private val key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(refreshTokenSecretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    override fun generateToken(userId: Long): String {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(Date(System.currentTimeMillis() + refreshTokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    override fun validateToken(token: String): Boolean {
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

    fun reissueToken(accessToken: String, refreshToken: String): Pair<String, String> {
        check(accessTokenProvider.validateTokenForReissue(accessToken)) { "AccessToken 검증 실패" }
        check(validateToken(refreshToken)) { "RefreshToken 검증 실패" }
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).body
        val userId = claims.subject.toLong()
        return accessTokenProvider.generateToken(userId) to generateToken(userId)
    }
}
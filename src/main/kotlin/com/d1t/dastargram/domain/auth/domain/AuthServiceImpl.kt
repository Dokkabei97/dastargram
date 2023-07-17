package com.d1t.dastargram.domain.auth.domain

import com.d1t.dastargram.domain.auth.dto.AuthRequest.LoginAuthRequest
import com.d1t.dastargram.domain.auth.dto.TokenDto
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.global.common.redis.RedisService
import com.d1t.dastargram.global.common.utils.AccessTokenProvider
import com.d1t.dastargram.global.common.utils.RefreshTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

/**
 * Auth 로직
 * Redis에 AccessToken과 RefreshToken을 저장한다.
 * Key 값은 각 AUTH:AT:email, AUTH:RT:email 이다.
 * Value 값은 각 AccessToken, RefreshToken 이다.
 */
@Service
@Transactional(readOnly = true)
class AuthServiceImpl(
        val memberReader: MemberReader,
        val redisService: RedisService,
        val accessTokenProvider: AccessTokenProvider,
        val refreshTokenProvider: RefreshTokenProvider,
        val authenticationManager: AuthenticationManagerBuilder
) : AuthService {

    @Transactional
    override fun login(loginAuthRequest: LoginAuthRequest): TokenDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(
                loginAuthRequest.email,
                loginAuthRequest.password
        )

        val authentication = authenticationManager
                .`object`
                .authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication

        return generateTokens(loginAuthRequest.email, getAuthorities(authentication))
    }

    @Transactional
    override fun logout() {

    }

    private fun generateTokens(email: String, authorities: String): TokenDto {
        if (redisService.findByKey("$ACCESS_TOKEN_REDIS_KEY:$email") != null) {
            redisService.delete("$ACCESS_TOKEN_REDIS_KEY:$email")
        } else if (redisService.findByKey("$REFRESH_TOKEN_REDIS_KEY:$email") != null) {
            redisService.delete("$REFRESH_TOKEN_REDIS_KEY:$email")
        }

        val accessToken = accessTokenProvider.generateToken(email, authorities)
        val refreshToken = refreshTokenProvider.generateToken()

        saveToken(email, accessToken, refreshToken)

        return TokenDto(accessToken, refreshToken)
    }

    private fun saveToken(email: String, accessToken: String, refreshToken: String) {
        redisService.save("$ACCESS_TOKEN_REDIS_KEY:$email", accessToken)
        redisService.save("$REFRESH_TOKEN_REDIS_KEY:$email", refreshToken)
    }


    private fun getAuthorities(authentication: Authentication): String {
        return authentication.authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
    }

    companion object {
        const val ACCESS_TOKEN_REDIS_KEY: String = "AUTH:AT"
        const val REFRESH_TOKEN_REDIS_KEY: String = "AUTH:RT"
    }
}
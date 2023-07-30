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
    override fun logout(accessTokenHeader: String) {
        val accessToken = resolveToken(accessTokenHeader)!!
        val principal = getPrincipal(accessToken)

        redisService.findByKey("$REFRESH_TOKEN_REDIS_KEY:$principal")?.let {
            redisService.delete("$REFRESH_TOKEN_REDIS_KEY:$principal")
        }
    }

    /**
     *
     * resolveToken() 함수에서 강력하게 null 체크를 하였기에 다른 함수들에서 !!를 사용해도 된다.
     */
    @Transactional
    override fun reissue(accessTokenHeader: String, refreshToken: String): TokenDto? {
        val accessToken = resolveToken(accessTokenHeader)!!

        val authentication = accessTokenProvider.getAuthentication(accessToken)
        val principal = getPrincipal(accessToken)
        check(validateAccessTokenExpired(accessToken)) { "AccessToken이 만료되지 않았습니다." }

        val redisRefreshToken = redisService.findByKey("$REFRESH_TOKEN_REDIS_KEY:$principal")
                ?: return null

        if (!refreshTokenProvider.validateToken(refreshToken) && refreshToken != redisRefreshToken) {
            redisService.delete("$REFRESH_TOKEN_REDIS_KEY:$principal")
            return null
        }

        SecurityContextHolder.getContext().authentication = authentication
        val authorities = getAuthorities(authentication)

        redisService.delete("$REFRESH_TOKEN_REDIS_KEY:$principal")
        return generateTokens(principal, authorities)
    }

    private fun resolveToken(accessTokenHeader: String?): String? {
        if (accessTokenHeader.isNullOrBlank()) {
            return null
        } else if (accessTokenHeader.isNotBlank() && accessTokenHeader.startsWith("Bearer ")) {
            return accessTokenHeader.substring(7)
        }
        return null
    }

    private fun validateAccessTokenExpired(token: String): Boolean {
        return accessTokenProvider.validateTokenForReissue(token)
    }

    private fun generateTokens(email: String, authorities: String): TokenDto {
        if (redisService.findByKey("$REFRESH_TOKEN_REDIS_KEY:$email") != null) {
            redisService.delete("$REFRESH_TOKEN_REDIS_KEY:$email")
        }

        val accessToken = accessTokenProvider.generateToken(email, authorities)
        val refreshToken = refreshTokenProvider.generateToken()

        saveRefreshToken(email, refreshToken)

        return TokenDto(accessToken, refreshToken)
    }

    private fun saveRefreshToken(email: String, refreshToken: String) {
        redisService.save("$REFRESH_TOKEN_REDIS_KEY:$email", refreshToken)
    }

    private fun getPrincipal(accessToken: String): String {
        return accessTokenProvider.getAuthentication(accessToken).name
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
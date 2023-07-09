package com.d1t.dastargram.domain.auth.domain

import com.d1t.dastargram.domain.auth.dto.AuthRequest.LoginAuthRequest
import com.d1t.dastargram.domain.auth.dto.TokenDto
import com.d1t.dastargram.domain.member.domain.MemberReader
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

@Service
@Transactional(readOnly = true)
class AuthServiceImpl(
        val accessTokenProvider: AccessTokenProvider,
        val refreshTokenProvider: RefreshTokenProvider,
        val memberReader: MemberReader,
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

        // TODO: accessToken, refreshToken Redis에 저장 
        
        return generateTokens(loginAuthRequest.email, getAuthorities(authentication))
    }

    @Transactional
    override fun logout() {
        TODO("Not yet implemented")
    }

    private fun generateTokens(email: String, authorities: String): TokenDto {
        val accessToken = accessTokenProvider.generateToken(email, authorities)
        val refreshToken = refreshTokenProvider.generateToken()

        return TokenDto(accessToken, refreshToken)
    }

    private fun getAuthorities(authentication: Authentication): String {
        return authentication.authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
    }
}
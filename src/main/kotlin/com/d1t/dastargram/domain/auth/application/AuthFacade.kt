package com.d1t.dastargram.domain.auth.application

import com.d1t.dastargram.domain.auth.domain.AuthService
import com.d1t.dastargram.domain.auth.dto.AuthRequest
import com.d1t.dastargram.domain.auth.dto.AuthRequest.*
import com.d1t.dastargram.domain.auth.dto.TokenDto
import org.springframework.stereotype.Service

@Service
class AuthFacade(val authService: AuthService) {
    fun login(loginAuthRequest: LoginAuthRequest) = authService.login(loginAuthRequest)
    fun logout(accessTokenHeader: String) = authService.logout(accessTokenHeader)
    fun reissue(accessTokenHeader: String, refreshToken: String) = authService.reissue(accessTokenHeader, refreshToken)
}
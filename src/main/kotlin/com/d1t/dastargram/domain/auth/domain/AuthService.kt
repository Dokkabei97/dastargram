package com.d1t.dastargram.domain.auth.domain

import com.d1t.dastargram.domain.auth.dto.AuthRequest.LoginAuthRequest
import com.d1t.dastargram.domain.auth.dto.TokenDto

interface AuthService {
    fun login(loginAuthRequest: LoginAuthRequest): TokenDto
    fun logout()
}
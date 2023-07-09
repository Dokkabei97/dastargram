package com.d1t.dastargram.domain.auth.api

import com.d1t.dastargram.domain.auth.application.AuthFacade
import com.d1t.dastargram.domain.auth.dto.AuthRequest
import com.d1t.dastargram.domain.auth.dto.AuthRequest.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(val authFacade: AuthFacade) {

    @PostMapping("/login")
    fun login(@RequestBody @Validated loginAuthRequest: LoginAuthRequest): CommonResponse<*> {
        authFacade.login(loginAuthRequest)
        return CommonResponse.success(Unit, "로그인 성공")
    }

    @PostMapping("/logout")
    fun logout(): CommonResponse<*> {
        return CommonResponse.success("로그아웃 성공")
    }
}
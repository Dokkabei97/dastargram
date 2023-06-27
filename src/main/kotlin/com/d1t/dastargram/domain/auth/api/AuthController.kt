package com.d1t.dastargram.domain.auth.api

import com.d1t.dastargram.domain.auth.application.AuthFacade
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(val authFacade: AuthFacade) {

    @PostMapping("/login")
    fun login(): CommonResponse<*> {
        return CommonResponse.success("로그인 성공")
    }

    @PostMapping("/logout")
    fun logout(): CommonResponse<*> {
        return CommonResponse.success("로그아웃 성공")
    }
}
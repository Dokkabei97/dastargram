package com.d1t.dastargram.domain.auth.api

import com.d1t.dastargram.domain.auth.application.AuthFacade
import com.d1t.dastargram.domain.auth.dto.AuthRequest.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(val authFacade: AuthFacade) {

    @Value("\${jwt.refresh-token.expire-time}")
    private val refreshTokenExpireTime: Long = 0

    @PostMapping("/login")
    fun login(@RequestBody @Validated loginAuthRequest: LoginAuthRequest): CommonResponse<ResponseEntity<Unit>> {
        val (accessToken, refreshToken) = authFacade.login(loginAuthRequest)

        val httpCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .maxAge(refreshTokenExpireTime)
                .secure(true)
                .build()

        return CommonResponse.success(
                ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                        .build(),
                "로그인 성공"
        )
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") accessTokenHeader: String): CommonResponse<ResponseEntity<Unit>> {
        authFacade.logout(accessTokenHeader)

        val responseCookie = ResponseCookie.from("refreshToken", "")
                .maxAge(0)
                .path("/")
                .build()

        return CommonResponse.success(
                ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                        .build(),
                "로그아웃 성공"
        )
    }

    @PostMapping("/reissue")
    fun reissue(
            @RequestHeader("Authorization") accessTokenHeader: String,
            @CookieValue(name = "refreshToken") refreshTokenCookie: String
    ): CommonResponse<ResponseEntity<Unit>> {
        authFacade.reissue(accessTokenHeader, refreshTokenCookie)?.let {
            val responseCookie = ResponseCookie.from("refreshToken", it.refreshToken)
                    .httpOnly(true)
                    .maxAge(refreshTokenExpireTime)
                    .secure(true)
                    .build()

            return CommonResponse.success(
                    ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                            .header(HttpHeaders.AUTHORIZATION, "Bearer ${it.accessToken}")
                            .build(),
                    "토큰 재발급 성공"
            )
        }

        val responseCookie = ResponseCookie.from("refreshToken", "")
                .maxAge(0)
                .path("/")
                .build()

        return CommonResponse.fail(
                ResponseEntity.status(401)
                        .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                        .build(),
                "토큰 재발급 실패",
                "INVALID_TOKEN"
        )
    }
}
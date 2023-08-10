package com.d1t.dastargram.domain.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

sealed class AuthRequest(

        @field: Email(message = "이메일 형식이 올바르지 않습니다.")
        @field: NotBlank(message = "이메일은 필수입니다.")
        open val email: String,

        @field: NotBlank(message = "비밀번호는 필수입니다.")
        open val password: String
) {

    data class LoginAuthRequest(
            override val email: String,
            override val password: String
    ) : AuthRequest(email, password)
}
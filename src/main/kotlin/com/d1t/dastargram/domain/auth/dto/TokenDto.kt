package com.d1t.dastargram.domain.auth.dto

import jakarta.validation.constraints.NotBlank

data class TokenDto(
        @field: NotBlank(message = "accessToken은 필수입니다.")
        val accessToken: String,
        @field: NotBlank(message = "refreshToken은 필수입니다.")
        val refreshToken: String
)

package com.d1t.dastargram.domain.auth.dto

data class TokenDto(
        val accessToken: String,
        val refreshToken: String
)

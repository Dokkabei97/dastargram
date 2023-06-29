package com.d1t.dastargram.global.common.utils

interface JwtTokenProvider {
    fun generateToken(userId: Long): String
    fun validateToken(token: String): Boolean
}
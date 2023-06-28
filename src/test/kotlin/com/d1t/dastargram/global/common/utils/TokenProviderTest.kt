package com.d1t.dastargram.global.common.utils

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class TokenProviderTest : AnnotationSpec() {

    private lateinit var accessTokenProvider: AccessTokenProvider
    private lateinit var refreshTokenProvider: RefreshTokenProvider

    init {
        @Test
        fun accessTokenTest() {
            val userId = 1L
            val token = accessTokenProvider.generateToken(userId)
            accessTokenProvider.validateToken(token) shouldBe true
        }
    }
}

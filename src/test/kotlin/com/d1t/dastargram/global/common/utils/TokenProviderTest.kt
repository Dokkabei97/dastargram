package com.d1t.dastargram.global.common.utils

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TokenProviderTest : BehaviorSpec({

    val accessTokenProvider = AccessTokenProvider(ACCESS_TOKEN_TEST_KEY, ACCESS_TOKEN_TEST_EXPIRE_TIME)
    val refreshTokenProvider = RefreshTokenProvider(REFRESH_TOKEN_TEST_KEY, REFRESH_TOKEN_TEST_EXPIRE_TIME, accessTokenProvider)

    given("AccessToken Provider") {
        val userId = 1L

        `when`("accessToken을 생성하면") {
            val accessToken = accessTokenProvider.generateToken(userId)

            then("accessToken은 생성된다") {
                println("accessToken: $accessToken")
                accessToken shouldNotBe null
            }

            then("accessToken은 유효하다") {
                accessTokenProvider.validateToken(accessToken) shouldBe true
            }

            then("1초가 지나면 만료가 된다") {
                Thread.sleep(1000)
                accessTokenProvider.validateToken(accessToken) shouldBe true
            }
        }
    }

    given("RefreshToken Provider") {
        val userId = 1L

        `when`("userId가 제공이되고") {
            val refreshToken = refreshTokenProvider.generateToken(userId)

            then("refreshToken은 생성된다") {
                println("refreshToken: $refreshToken")
                refreshToken shouldNotBe null
            }

            then("refreshToken은 유효하다") {
                refreshTokenProvider.validateToken(refreshToken) shouldBe true
            }

            then("2초가 지나면 만료가 된다") {
                Thread.sleep(2000)
                refreshTokenProvider.validateToken(refreshToken) shouldBe false
            }
        }
    }
}) {
    companion object {
        const val ACCESS_TOKEN_TEST_KEY: String = "ZGy3/59hPszXVia2gzpIwOXcPDWYljEbGsQDMigEuZY="
        const val ACCESS_TOKEN_TEST_EXPIRE_TIME: Long = 1000L
        const val REFRESH_TOKEN_TEST_KEY: String = "VI7tgck4PV55Tj7O5ZVbWgIN2scM/UqEX9Zvs68TeOw="
        const val REFRESH_TOKEN_TEST_EXPIRE_TIME: Long = 2000L
    }
}
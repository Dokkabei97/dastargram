package com.d1t.dastargram.global.common.utils

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.security.core.userdetails.UserDetailsService

class TokenProviderTest : BehaviorSpec({

    val accessTokenProvider = AccessTokenProvider(ACCESS_TOKEN_TEST_KEY, ACCESS_TOKEN_TEST_EXPIRE_TIME, UserDetailsService { null })
    val refreshTokenProvider = RefreshTokenProvider(REFRESH_TOKEN_TEST_KEY, REFRESH_TOKEN_TEST_EXPIRE_TIME, UserDetailsService { null })

    fun reissueTokenTest(accessToken: String, refreshToken: String): Pair<String, String> {
        check(accessTokenProvider.validateTokenForReissue(accessToken)) { "accessToken이 유효하지 않습니다." }
        check(refreshTokenProvider.validateToken(refreshToken)) { "refreshToken이 유효하지 않습니다." }

        val claims = accessTokenProvider.getClaims(accessToken)
        val email = claims["email"] as String
        val authorities = claims["role"] as String
        return accessTokenProvider.generateToken(email, authorities) to refreshTokenProvider.generateToken()
    }

    given("AccessToken Provider") {
        val email = "test@test.com"
        val authorities = "ROLE_USER"

        `when`("accessToken을 생성하면") {
            val accessToken = accessTokenProvider.generateToken(email, authorities)

            then("accessToken은 생성된다") {
                accessToken shouldNotBe null
            }

            then("accessToken은 유효하다") {
                accessTokenProvider.validateToken(accessToken) shouldBe true
            }

            then("1초가 지나면 만료가 된다") {
                Thread.sleep(1000)
                accessTokenProvider.validateToken(accessToken) shouldBe false
            }

            then("1초가 지나면 재발급용 검증 코드에서는 유효하다") {
                accessTokenProvider.validateTokenForReissue(accessToken) shouldBe true
            }
        }

        `when`("위조 jwt토큰을 검증하면") {
            then("false를 반환한다") {
                accessTokenProvider.validateToken(COUNTERFEIT_TOKEN) shouldBe false
            }
        }

        `when`("generateToken() 함수를 호출시") {
            then("RefreshTokenProvider가 아니면 에러가 발생한다") {
                shouldThrow<IllegalStateException> {
                    accessTokenProvider.generateToken()
                }
            }
        }
    }

    given("RefreshToken Provider") {
        `when`("userId가 제공이되고") {
            val refreshToken = refreshTokenProvider.generateToken()

            then("refreshToken은 생성된다") {
                refreshToken shouldNotBe null
            }

            then("refreshToken은 유효하다") {
                refreshTokenProvider.validateToken(refreshToken) shouldBe true
            }

            then("2초가 지나면 만료가 된다") {
                Thread.sleep(3000)
                refreshTokenProvider.validateToken(refreshToken) shouldBe false
            }
        }

        `when`("위조 jwt토큰을 검증하면") {
            then("false를 반환한다") {
                refreshTokenProvider.validateToken(COUNTERFEIT_TOKEN) shouldBe false
            }
        }

        `when`("generateToken(email, authorities) 함수를 호출시") {
            then("AccessTokenProvider가 아니면 에러가 발생한다") {
                shouldThrow<IllegalStateException> {
                    refreshTokenProvider.generateToken("", "")
                }
            }
        }
    }

    given("AccessToken과 RefreshToken이 주어지고") {
        val email = "test@test.com"
        val authorities = "ROLE_USER"
        val accessToken = accessTokenProvider.generateToken(email, authorities)
        val refreshToken = refreshTokenProvider.generateToken()
        `when`("토큰을 재발급을 요청하면") {
            Thread.sleep(1200)
            val newAccessToken = reissueTokenTest(accessToken, refreshToken)

            then("새로운 accessToken이 발급된다") {
                newAccessToken shouldNotBe null
            }

            then("새로운 accessToken은 유효하다") {
                accessTokenProvider.validateToken(newAccessToken.first) shouldBe true
            }

            then("새로운 accessToken은 기존 accessToken과 다르다") {
                newAccessToken shouldNotBe accessToken
            }
        }

        `when`("토큰을 재발급을 요청하는데 refreshToken이 만료되면") {
            Thread.sleep(3000)
            then("IllegalStateException이 나온다") {
                val exception = shouldThrow<IllegalStateException> {
                    reissueTokenTest(accessToken, refreshToken)
                }
            }
        }
    }

}) {
    companion object {
        const val ACCESS_TOKEN_TEST_KEY: String = "ZGy3/59hPszXVia2gzpIwOXcPDWYljEbGsQDMigEuZY="
        const val ACCESS_TOKEN_TEST_EXPIRE_TIME: Long = 1000L
        const val REFRESH_TOKEN_TEST_KEY: String = "VI7tgck4PV55Tj7O5ZVbWgIN2scM/UqEX9Zvs68TeOw="
        const val REFRESH_TOKEN_TEST_EXPIRE_TIME: Long = 3000L
        const val COUNTERFEIT_TOKEN: String = "eyJhbGCIoDanawa1NiJ9.EYjZDwiiOiIxConnectWavexNjg4MDM4MDI5fQ.Ktc8Y2SnDXktORassslgxsiKFCLPeAMSd45M3JGzlYA"
    }
}
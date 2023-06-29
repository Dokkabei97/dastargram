package com.d1t.dastargram.domain.auth.domain

import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.global.common.utils.AccessTokenProvider
import com.d1t.dastargram.global.common.utils.RefreshTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthServiceImpl(
        val accessTokenProvider: AccessTokenProvider,
        val refreshTokenProvider: RefreshTokenProvider,
        val memberReader: MemberReader
) : AuthService {

    companion object {
        private const val REDIS_ACCESS_TOKEN_KEY: String = "auth:member:%d:accessToken"
        private const val REDIS_REFRESH_TOKEN_KEY: String = "auth:member:%d:refreshToken"
    }

    @Transactional
    override fun login() {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun logout() {
        TODO("Not yet implemented")
    }
}
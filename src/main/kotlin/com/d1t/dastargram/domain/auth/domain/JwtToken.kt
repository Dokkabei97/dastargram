package com.d1t.dastargram.domain.auth.domain

import org.springframework.data.redis.core.RedisHash

@RedisHash("jwtToken")
class JwtToken {
}
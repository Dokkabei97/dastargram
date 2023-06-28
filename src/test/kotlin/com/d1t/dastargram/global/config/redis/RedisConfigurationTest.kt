package com.d1t.dastargram.global.config.redis

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate

class RedisConfigurationTest : AnnotationSpec() {

    private lateinit var redisTemplate: StringRedisTemplate

    init {
        @Test
        fun redisConnectTest() {
            redisTemplate.opsForValue().set("test", "test")

            redisTemplate.opsForValue().get("test") shouldBe "test"
        }
    }
}

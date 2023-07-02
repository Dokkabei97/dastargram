package com.d1t.dastargram.global.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfiguration {

    /**
     * 코틀린에서는 String 문자열에 $를 붙여서 변수를 사용할 수 있다보니
     *  @Value() 를 사용할 때 \를 붙여 이스케이프를 해줘야 한다.
     *  @Value()로 값을 주입 받다보니 var을 사용해야 한다.
     */
    @Value("\${spring.data.redis.host}")
    var host: String = "localhost"

    @Value("\${spring.data.redis.port}")
    var port: Int = 6379

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    // RedisTemplate 설정
    // RedisTemplate은 Redis에 데이터를 저장하고 조회하기 위한 인터페이스
    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        return redisTemplate
    }

    // StringRedisTemplate 설정
    // StringRedisTemplate은 RedisTemplate을 상속받아 String 타입의 데이터를 저장하고 조회하기 위한 인터페이스
    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        val stringRedisTemplate = StringRedisTemplate()
        stringRedisTemplate.connectionFactory = redisConnectionFactory()
        stringRedisTemplate.keySerializer = StringRedisSerializer()
        stringRedisTemplate.valueSerializer = StringRedisSerializer()
        return stringRedisTemplate
    }
}
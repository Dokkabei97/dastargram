package com.d1t.dastargram.global.common.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit


/**
 * .opsForValue() : Redis의 String 타입 데이터를 저장하고 조회하는 기능
 * .opsForHash() : Redis의 Hash 타입 데이터를 저장하고 조회하는 기능
 * .opsForList() : Redis의 List 타입 데이터를 저장하고 조회하는 기능
 * .opsForSet() : Redis의 Set 타입 데이터를 저장하고 조회하는 기능
 * .opsForZSet() : Redis의 ZSet 타입 데이터를 저장하고 조회하는 기능
 */
@Service
@Transactional(readOnly = true)
class RedisServiceImpl(val redisTemplate: RedisTemplate<Any, Any>) : RedisService {

    @Transactional
    override fun save(key: Any, value: Any) = redisTemplate.opsForValue().set(key, value)
    @Transactional
    override fun saveWithTimeout(key: Any, value: Any, timeout: Long) = redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS)
    override fun findByKey(key: Any): Any? = redisTemplate.opsForValue().get(key)
    @Transactional
    override fun delete(key: Any) {
        redisTemplate.delete(key)
    }
}
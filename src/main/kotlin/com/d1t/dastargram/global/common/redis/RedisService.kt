package com.d1t.dastargram.global.common.redis

interface RedisService {

    fun save(key: Any, value: Any)
    fun saveWithTimeout(key: Any, value: Any, timeout: Long)
    fun findByKey(key: Any): Any?
    fun delete(key: Any)
}
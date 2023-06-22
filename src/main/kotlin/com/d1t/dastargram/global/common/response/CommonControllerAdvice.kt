package com.d1t.dastargram.global.common.response

import com.d1t.dastargram.global.common.interceptor.CommonRequestInterceptor
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonControllerAdvice {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(e: Exception): CommonResponse<*> {
        val eventId = MDC.get(CommonRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.error("eventId = {}", eventId, e)
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }
}
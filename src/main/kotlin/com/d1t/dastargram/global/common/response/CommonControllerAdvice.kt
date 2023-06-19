package com.d1t.dastargram.global.common.response

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonControllerAdvice {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    fun onException(e: Exception): CommonResponse<*> {
        log.error("예외 발생", e)
        return CommonResponse.fail("예외 발생", HttpStatus.INTERNAL_SERVER_ERROR.toString())
    }
}
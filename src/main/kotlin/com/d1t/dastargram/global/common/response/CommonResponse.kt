package com.d1t.dastargram.global.common.response

class CommonResponse<T>(
        val result: Result,
        val data: T,
        val message: String?,
        val error: String?
) {

    enum class Result {
        SUCCESS, FAIL
    }

    companion object {
        fun <T> success(data: T, message: String?): CommonResponse<T> = CommonResponse(Result.SUCCESS, data, message, null)
        fun fail(message: String?, errorCode: String?): CommonResponse<*> = CommonResponse(Result.FAIL, null, message, errorCode)
    }
}
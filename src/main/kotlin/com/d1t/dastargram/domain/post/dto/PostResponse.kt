package com.d1t.dastargram.domain.post.dto

sealed class PostResponse(
    open val content: String?,
    open val likeCount: Int = 0,
    open val postImages: List<String>
) {

    data class PostPublicResponse(
        val id: Long,
        override val content: String?,
        override val likeCount: Int = 0,
        override val postImages: List<String>
    ): PostResponse(content, likeCount, postImages)
}
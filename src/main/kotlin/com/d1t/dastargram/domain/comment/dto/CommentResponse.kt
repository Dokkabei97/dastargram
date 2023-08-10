package com.d1t.dastargram.domain.comment.dto

sealed class CommentResponse(
    open val likeCount: Int,
    open val content: String,
) {
    data class PublicResponse(
        val id: Long,
        override val likeCount: Int,
        override val content: String
    ): CommentResponse(likeCount, content)
}
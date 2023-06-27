package com.d1t.dastargram.domain.comment.dto

data class CommentResponse (
    val postId: Long?,
    val memberId: Long?,
    val content: String,
)
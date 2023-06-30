package com.d1t.dastargram.domain.comment.dto

data class CommentResponse (
    val likeCount: Int,
    val content: String,
)
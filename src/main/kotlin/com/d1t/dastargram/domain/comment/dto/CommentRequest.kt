package com.d1t.dastargram.domain.comment.dto

import jakarta.validation.constraints.NotBlank

class CommentRequest {

    data class createCommentRequest(
        @field:NotBlank
        val content: String,
        @field:NotBlank
        val postId: Long,
        @field:NotBlank
        val memberId: Long,
    )

    data class modifyCommentRequest(
        @field:NotBlank
        val id: Long,
        @field:NotBlank
        val content: String,
        @field:NotBlank
        val memberId: Long,
    )

    data class removeCommentRequest(
        @field:NotBlank
        val id: Long,
    )
}
package com.d1t.dastargram.domain.comment.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class CommentRequest {

    data class insertRequest(
        @field:NotBlank
        val content: String,
        @field:NotNull
        val postId: Long,
        @field:NotNull
        val memberId: Long,
    )

    data class updateRequest(
        @field:NotNull
        val id: Long,
        @field:NotBlank
        val content: String,
        @field:NotNull
        val memberId: Long,
    )

    data class deleteRequest(
        @field:NotNull
        val id: Long,
        @field:NotNull
        val memberId: Long
    )
}
package com.d1t.dastargram.domain.comment.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

sealed class CommentRequest (
) {

    data class InsertRequest(
        @field:NotBlank
        val content: String,
        @field:NotNull
        val postId: Long,
        @field:NotNull
        val memberId: Long,
    )

    data class UpdateRequest(
        @field:NotNull
        val id: Long,
        @field:NotBlank
        val content: String,
        @field:NotNull
        val memberId: Long,
    )

    data class DeleteRequest(
        @field:NotNull
        val id: Long,
        @field:NotNull
        val memberId: Long
    )
}
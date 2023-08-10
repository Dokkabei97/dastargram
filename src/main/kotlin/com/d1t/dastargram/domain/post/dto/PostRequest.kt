package com.d1t.dastargram.domain.post.dto

import jakarta.validation.constraints.NotNull

sealed class PostRequest(
    @field: NotNull(message = "사진은 필수입니다.")
    open val postImages: List<String>,
) {
    data class UploadPostRequest(
        val memberId: Long,
        val content: String?,
        override val postImages: List<String>,
    ): PostRequest(postImages)

    data class UpdatePostRequest(
        val memberId: Long,
        val postId: Long,
        val content: String?
    )

    data class DeletePostRequest(
        val memberId: Long,
        val postId: Long
    )
}
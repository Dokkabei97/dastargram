package com.d1t.dastargram.domain.post.dto

import jakarta.validation.constraints.NotNull

sealed class PostRequest(
    open val content: String,

    @field: NotNull(message = "사진은 필수입니다.")
    open val postImages: List<String>,
) {
    data class UploadPostRequest(
        override val content: String,
        override val postImages: List<String>,
    ) : PostRequest(content, postImages)

    data class UpdatePostRequest(
         val content: String?
    )

    data class DeletePostRequest(
        val postId: Long
    )
}
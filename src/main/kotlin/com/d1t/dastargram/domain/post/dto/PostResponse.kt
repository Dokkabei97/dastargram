package com.d1t.dastargram.domain.post.dto

import jakarta.persistence.Id

sealed class PostResponse(
    open val postId: Long,
    open val content: String?,
    open val profileImage: String? = null,
    open val nickname: String,
) {

    data class PostResponse(
    ) : PostResponse()
}
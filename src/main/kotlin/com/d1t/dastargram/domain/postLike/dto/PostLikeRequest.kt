package com.d1t.dastargram.domain.postLike.dto

sealed class PostLikeRequest (
    open val postId: Long,
    open val memberId: Long
) {
    data class InsertRequestPost (
        override val postId: Long,
        override val memberId: Long
    ) : PostLikeRequest(postId, memberId)
}
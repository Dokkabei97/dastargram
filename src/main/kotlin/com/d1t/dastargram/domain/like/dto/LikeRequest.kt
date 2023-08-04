package com.d1t.dastargram.domain.like.dto

import org.jetbrains.annotations.NotNull

class LikeRequest {
    data class insertRequest(
        @field:NotNull
        val memberId: Long,
        @field:NotNull
        val postId: Long
    )
}
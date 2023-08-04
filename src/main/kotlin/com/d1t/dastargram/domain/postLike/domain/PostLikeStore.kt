package com.d1t.dastargram.domain.postLike.domain

interface PostLikeStore {
    fun create(postLike: PostLike): PostLike
    fun deleteById(likeId: Long)
}
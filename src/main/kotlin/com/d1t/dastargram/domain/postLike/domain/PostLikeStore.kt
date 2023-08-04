package com.d1t.dastargram.domain.postLike.domain

interface PostLikeStore {
    fun create(like: PostLike): PostLike
    fun deleteById(likeId: Long)
}
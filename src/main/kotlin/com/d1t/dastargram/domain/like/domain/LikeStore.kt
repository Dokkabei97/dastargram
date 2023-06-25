package com.d1t.dastargram.domain.like.domain

interface LikeStore {
    fun create(like: Like): Like
    fun deleteById(likeId: Long)
}
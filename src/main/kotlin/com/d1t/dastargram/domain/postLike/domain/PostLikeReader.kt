package com.d1t.dastargram.domain.postLike.domain

interface PostLikeReader {
    fun getPostLikeById(id:Long): PostLike
}
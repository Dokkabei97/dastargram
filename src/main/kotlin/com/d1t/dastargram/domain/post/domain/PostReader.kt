package com.d1t.dastargram.domain.post.domain

interface PostReader {
    fun getPostById(memberId: Long): Post
}
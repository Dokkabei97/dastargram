package com.d1t.dastargram.domain.post.domain

interface PostReader {
    fun findById(memberId: Long): Post
}
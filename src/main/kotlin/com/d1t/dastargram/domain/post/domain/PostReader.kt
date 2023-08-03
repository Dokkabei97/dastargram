package com.d1t.dastargram.domain.post.domain

interface PostReader {
    fun findById(postId: Long): Post
    fun findByMemberId(memberId: Long): List<Post>
}
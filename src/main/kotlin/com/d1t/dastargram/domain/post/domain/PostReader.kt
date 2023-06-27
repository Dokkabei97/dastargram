package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member

interface PostReader {
    fun findById(postId: Long): Post
}
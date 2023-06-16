package com.d1t.dastargram.domain.post.infrastructure

import com.d1t.dastargram.domain.post.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
}
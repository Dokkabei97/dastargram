package com.d1t.dastargram.domain.postLike.infrastructure

import com.d1t.dastargram.domain.postLike.domain.PostLike
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository: JpaRepository<PostLike, Long> {
}
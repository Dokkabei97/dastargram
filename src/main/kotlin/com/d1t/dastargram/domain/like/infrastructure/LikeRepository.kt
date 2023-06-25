package com.d1t.dastargram.domain.like.infrastructure

import com.d1t.dastargram.domain.like.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long> {
}
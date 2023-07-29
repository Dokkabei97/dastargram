package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository: JpaRepository<Follow, Long> {
}
package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface FollowRepository : JpaRepository<Follow, Long> {

    @Modifying
    @Query("DELETE FROM Follow f WHERE f.followerMemberId = :followerMemberId AND f.followingMemberId = :followingMemberId")
    fun deleteFollow(followerMemberId: Long, followingMemberId: Long)

    fun findByFollowerMemberId(followerMemberId: Long): List<Follow>?
    fun findByFollowingMemberId(followingMemberId: Long): List<Follow>?
    fun existsByFollowerMemberIdAndFollowingMemberId(followerMemberId: Long, followingMemberId: Long): Boolean
}
package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import com.d1t.dastargram.domain.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface FollowRepository: JpaRepository<Follow, Long> {

    @Modifying
    @Query("DELETE FROM Follow f WHERE f.followerMemberId = :followerMemberId AND f.followingMemberId = :followingMemberId")
    fun deleteFollow(followerMemberId: Long, followingMemberId: Long)

    fun findByFollowerMemberId(id: Long) :List<Follow>
}
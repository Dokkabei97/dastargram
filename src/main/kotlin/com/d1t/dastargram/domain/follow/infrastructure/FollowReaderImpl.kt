package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import com.d1t.dastargram.domain.follow.domain.FollowReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component

@Component
class FollowReaderImpl(val followRepository: FollowRepository) : FollowReader {
    override fun getFollowers(memberId: Long): List<Follow> = followRepository.findByFollowerMemberId(memberId)
            ?: emptyList()

    override fun getFollowings(memberId: Long): List<Follow> = followRepository.findByFollowingMemberId(memberId)
            ?: emptyList()

    override fun isExistByFollowerIdAndFollowingId(followerMemberId: Long, followingMemberId: Long): Boolean =
            followRepository.existsByFollowerMemberIdAndFollowingMemberId(followerMemberId, followingMemberId)
}
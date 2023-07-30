package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import com.d1t.dastargram.domain.follow.domain.FollowStore
import org.springframework.stereotype.Component

@Component
class FollowStoreImpl(val followRepository: FollowRepository) : FollowStore {

    override fun create(follow: Follow): Follow = followRepository.save(follow)

    override fun deleteByFollowerIdAndFollowingId(followerMemberId: Long, followingMemberId: Long) {
        followRepository.deleteFollow(followerMemberId, followingMemberId)
    }
}
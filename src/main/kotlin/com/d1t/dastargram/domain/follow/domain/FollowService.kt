package com.d1t.dastargram.domain.follow.domain

import com.d1t.dastargram.domain.follow.dto.FollowRequest.UpdateFollowRequest

interface FollowService {
    fun follow(request: UpdateFollowRequest): Follow
    fun unfollow(request: UpdateFollowRequest)
    fun getFollowers(memberId: Long): List<Follow>
    fun getFollowings(memberId: Long): List<Follow>
    fun isFollowing(followerMemberId: Long, followingMemberId: Long): Boolean
}
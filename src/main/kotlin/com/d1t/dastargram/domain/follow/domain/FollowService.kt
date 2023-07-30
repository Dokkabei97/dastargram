package com.d1t.dastargram.domain.follow.domain

import com.d1t.dastargram.domain.follow.dto.FollowRequest.UpdateFollowRequest

interface FollowService {
    fun follow(request: UpdateFollowRequest): Follow
    fun unfollow(request: UpdateFollowRequest)
    fun getFollowers(request: Long): List<Follow>
    fun getFollowings(request: Long): List<Follow>
    fun isFollowing(request: UpdateFollowRequest): Boolean
}
package com.d1t.dastargram.domain.follow.domain

interface FollowStore {
    fun create(follow: Follow): Follow
    fun deleteByFollowerIdAndFollowingId(followerMemberId: Long, followingMemberId: Long)
}
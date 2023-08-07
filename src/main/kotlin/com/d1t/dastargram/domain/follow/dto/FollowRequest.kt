package com.d1t.dastargram.domain.follow.dto

sealed class FollowRequest {

    data class UpdateFollowRequest(
            val followerMemberId: Long,
            val followingMemberId: Long,
    )
}

package com.d1t.dastargram.domain.follow.dto

sealed class FollowRequest(

        open val followerMemberId: Long,
        open val followingMemberId: Long,
) {

    data class UpdateFollowRequest(
            override val followerMemberId: Long,
            override val followingMemberId: Long,
    ) : FollowRequest(followerMemberId, followingMemberId)

    data class GetFollowInfoRequest (
            val memberId: Long
    )
}

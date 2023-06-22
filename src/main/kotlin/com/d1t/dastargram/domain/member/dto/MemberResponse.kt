package com.d1t.dastargram.domain.member.dto

sealed class MemberResponse(
        open val nickname: String,
        open val name: String,
        open val profileImage: String? = null,
        open val followerCount: Int = 0,
        open val followingCount: Int = 0,
) {

    data class MemberPublicResponse(
            override val nickname: String,
            override val name: String,
            override val profileImage: String? = null,
            override val followerCount: Int = 0,
            override val followingCount: Int = 0,
    ) : MemberResponse(nickname, name, profileImage, followerCount, followingCount)

    data class MemberPrivateResponse(
            val id: Long,
            val email: String,
            override val nickname: String,
            override val name: String,
            override val profileImage: String? = null,
            override val followerCount: Int = 0,
            override val followingCount: Int = 0,
    ) : MemberResponse(nickname, name, profileImage, followerCount, followingCount)
}
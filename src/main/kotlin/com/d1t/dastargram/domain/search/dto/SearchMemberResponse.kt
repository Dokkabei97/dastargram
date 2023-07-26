package com.d1t.dastargram.domain.search.dto

sealed class SearchMemberResponse (
    open val id: Long,
    open val name: String,
    open val nickname: String,
    open val profileImage:String ?= null
) {
    data class SearchMemberPublicResponse(
        override val id: Long,
        override val nickname: String,
        override val name: String,
        override val profileImage: String? = null
    ) : SearchMemberResponse(id, nickname, name, profileImage)

    data class SearchMemberPrivateResponse(
        override val id: Long,
        val email: String,
        override val nickname: String,
        override val name: String,
        override val profileImage: String? = null,
        val followerCount: Int = 0,
        val followingCount: Int = 0,
    ) : SearchMemberResponse(id, nickname, name, profileImage)
}
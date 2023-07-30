package com.d1t.dastargram.domain.search.dto

sealed class SearchMemberResponse(
    val id: Long,
    val name: String,
    val nickname: String,
    val profileImage: String? = null
)
package com.d1t.dastargram.domain.follow.domain

interface FollowReader {
    fun getFollowers(memberId: Long): List<Follow>
    fun getFollowings(memberId: Long): List<Follow>
}
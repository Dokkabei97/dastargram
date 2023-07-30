package com.d1t.dastargram.domain.follow.domain

interface FollowReader {
    fun getFollowingsById(accessedId: Long) :List<Follow>
}
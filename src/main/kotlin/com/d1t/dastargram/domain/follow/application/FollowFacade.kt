package com.d1t.dastargram.domain.follow.application

import com.d1t.dastargram.domain.follow.domain.Follow
import com.d1t.dastargram.domain.follow.domain.FollowService
import com.d1t.dastargram.domain.follow.dto.FollowRequest
import com.d1t.dastargram.domain.follow.dto.FollowRequest.*
import org.springframework.stereotype.Service

@Service
class FollowFacade(val followService: FollowService) {
    fun follow(request: UpdateFollowRequest) = followService.follow(request)
    fun unfollow(request: UpdateFollowRequest) = followService.unfollow(request)
    fun getFollowers(request: Long): List<Follow> = followService.getFollowers(request)
    fun getFollowings(request: Long): List<Follow> = followService.getFollowings(request)
}
package com.d1t.dastargram.domain.follow.api

import com.d1t.dastargram.domain.follow.application.FollowFacade
import com.d1t.dastargram.domain.follow.dto.FollowRequest
import com.d1t.dastargram.domain.follow.dto.FollowRequest.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/follows")
class FollowController(val followFacade: FollowFacade) {

    @GetMapping("/followers")
    fun getFollowers(@RequestParam memberId: Long): CommonResponse<*> {
        val followers = followFacade.getFollowers(memberId)
        if (followers.isEmpty()) {
            return CommonResponse.success("팔로워가 없습니다.")
        }
        return CommonResponse.success(followers, "팔로워 조회 성공")
    }

    @GetMapping("/followings")
    fun getFollowings(@RequestParam memberId: Long): CommonResponse<*> {
        val followings = followFacade.getFollowings(memberId)
        if (followings.isEmpty()) {
            return CommonResponse.success("팔로잉이 없습니다.")
        }
        return CommonResponse.success(followings, "팔로잉 조회 성공")
    }

    @PostMapping
    fun follow(@RequestBody @Validated request: UpdateFollowRequest): CommonResponse<*> {
        followFacade.follow(request)
        return CommonResponse.success("팔로우 성공")
    }

    @DeleteMapping
    fun unfollow(@RequestBody @Validated request: UpdateFollowRequest): CommonResponse<*> {
        followFacade.unfollow(request)
        return CommonResponse.success("언팔로우 성공")
    }
}
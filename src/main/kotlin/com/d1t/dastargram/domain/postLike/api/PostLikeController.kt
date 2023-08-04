package com.d1t.dastargram.domain.postLike.api

import com.d1t.dastargram.domain.postLike.application.PostLikeFacade
import com.d1t.dastargram.domain.postLike.dto.PostLikeRequest
import com.d1t.dastargram.domain.postLike.dto.PostLikeResponse
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/likes")
class PostLikeController (val postLikeFacade: PostLikeFacade) {
    @GetMapping
    fun index(): String {
        return "LIKE";
    }

    @PostMapping
    fun createLike(@RequestBody @Validated insertRequest: PostLikeRequest.InsertRequestPost) : CommonResponse<PostLikeResponse> {
        val like = postLikeFacade.insert(insertRequest)
        return CommonResponse.success(like, "좋아요 하기 성공")
    }

}
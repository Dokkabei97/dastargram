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
    fun createLike(@RequestBody @Validated insertRequest: PostLikeRequest.InsertRequest) : CommonResponse<PostLikeResponse> {
        val like = postLikeFacade.insert(insertRequest)
        return CommonResponse.success(like, "좋아요 하기 성공")
    }

    @DeleteMapping
    fun cancelLike(@RequestBody @Validated deleteRequest: PostLikeRequest.DeleteRequest) : CommonResponse<Nothing?> {
        val like = postLikeFacade.delete(deleteRequest)
        return CommonResponse.success(null, "좋아요 취소 성공")
    }

}
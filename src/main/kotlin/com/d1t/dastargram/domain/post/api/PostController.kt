package com.d1t.dastargram.domain.post.api

import com.d1t.dastargram.domain.post.application.PostFacade
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController(val postFacade: PostFacade) {

    @PostMapping
    fun upload(
        @RequestBody @Validated uploadPostRequest: UploadPostRequest
    ): CommonResponse<PostPublicResponse> {
        val post = postFacade.upload(uploadPostRequest)
        return CommonResponse.success(post, "게시물 업로드 성공")
    }

    @DeleteMapping
    fun delete(
        @RequestBody @Validated deletePostRequest: DeletePostRequest
    ): CommonResponse<Unit?> {
        postFacade.delete(deletePostRequest)
        return CommonResponse.success(null,"게시물 삭제 성공")
    }


    @PutMapping
    fun update(
        @RequestBody @Validated updatePostRequest: UpdatePostRequest
    ): CommonResponse<PostPublicResponse> {
        val post = postFacade.update(updatePostRequest)
        return CommonResponse.success(post, "게시물 업데이트 성공")
    }

    @GetMapping("/{memberId}")
    fun getPosts(
        @PathVariable memberId: Long
    ): CommonResponse<List<PostPublicResponse>> {
        val posts = postFacade.getPosts(memberId)
        return CommonResponse.success(posts, "회원 게시물 목록 조회 성공")
    }
}
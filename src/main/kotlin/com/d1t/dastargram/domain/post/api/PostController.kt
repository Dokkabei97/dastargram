package com.d1t.dastargram.domain.post.api

import com.d1t.dastargram.domain.post.application.PostFacade
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController(val postFacade: PostFacade) {
    @PostMapping
    fun upload(@RequestBody @Validated uploadPostRequest: UploadPostRequest): CommonResponse<PostPublicResponse> {
        //TODO 로그인 기능 완성 후 수정 예정
        val post = postFacade.upload(1, uploadPostRequest)
        return CommonResponse.success(post, "게시물 업로드 성공")
    }

    @DeleteMapping
    fun delete(@RequestBody @Validated deletePostRequest: DeletePostRequest): CommonResponse<Nothing?> {
        //TODO 로그인 기능 완성 후 수정 예정
        postFacade.delete(1, deletePostRequest)
        return CommonResponse.success(null,"게시물 삭제 성공")
    }


    @PutMapping
    fun update(@RequestBody @Validated updatePostRequest: UpdatePostRequest): CommonResponse<PostPublicResponse> {
        //TODO 로그인 기능 완성 후 수정 예정
        val post = postFacade.update(1, updatePostRequest)
        return CommonResponse.success(post, "게시물 업데이트 성공")
    }
}
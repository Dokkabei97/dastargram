package com.d1t.dastargram.domain.post.api

import com.d1t.dastargram.domain.post.application.PostFacade
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController(val postFacade: PostFacade) {
    @PostMapping
    fun upload(@RequestBody @Validated uploadPostRequest: UploadPostRequest): CommonResponse<> {
        //TODO 로그인 기능 완성 후 수정 예정
        postFacade.upload(1, uploadPostRequest);
        return ResponseEntity.ok("게시물 업로드 성공")
    }

    @DeleteMapping
    fun delete(@RequestBody @Validated deletePostRequest: DeletePostRequest): ResponseEntity<String> {
        //TODO 로그인 기능 완성 후 수정 예정
        postFacade.delete(deletePostRequest);
        return ResponseEntity.ok("게시물 업로드 성공")
    }

    @PutMapping
    fun update(@RequestBody @Validated updatePostRequest: UpdatePostRequest): ResponseEntity<String> {
        //TODO 로그인 기능 완성 후 수정 예정
        postFacade.update(updatePostRequest);
        return ResponseEntity.ok("게시물 업로드 성공")
    }
}
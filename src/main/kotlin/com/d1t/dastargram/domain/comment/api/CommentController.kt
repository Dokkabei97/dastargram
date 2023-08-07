package com.d1t.dastargram.domain.comment.api

import com.d1t.dastargram.domain.comment.application.CommentFacade
import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comments")
class CommentController(val commentFacade: CommentFacade) {

    @PostMapping
    fun create(@RequestBody @Validated insertRequest: CommentRequest.InsertRequest): CommonResponse<CommentResponse> {
        val comment = commentFacade.insert(insertRequest)
        return CommonResponse.success(comment, "댓글 등록 성공")
    }

    @PutMapping
    fun modify(@RequestBody @Validated updateRequest: CommentRequest.UpdateRequest): CommonResponse<CommentResponse> {
        val comment = commentFacade.update(updateRequest)
        return CommonResponse.success(comment, "댓글 수정 성공")
    }

    @DeleteMapping
    fun remove(@RequestBody @Validated deleteRequest: CommentRequest.DeleteRequest): CommonResponse<Unit?> {
        commentFacade.delete(deleteRequest)
        return CommonResponse.success(null, "댓글 삭제 성공")
    }
}

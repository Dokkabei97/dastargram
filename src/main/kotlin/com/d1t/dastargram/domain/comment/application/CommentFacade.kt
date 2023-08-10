package com.d1t.dastargram.domain.comment.application

import com.d1t.dastargram.domain.comment.domain.CommentService
import com.d1t.dastargram.domain.comment.dto.CommentRequest
import org.springframework.stereotype.Service

@Service
class CommentFacade(val commentService: CommentService) {

    fun insert(insertRequest: CommentRequest.InsertRequest) = commentService.insert(insertRequest)
    fun update(updateRequest: CommentRequest.UpdateRequest) = commentService.update(updateRequest)

    fun delete(deleteRequest: CommentRequest.DeleteRequest) = commentService.delete(deleteRequest)
}
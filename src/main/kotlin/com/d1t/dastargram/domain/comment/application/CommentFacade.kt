package com.d1t.dastargram.domain.comment.application

import com.d1t.dastargram.domain.comment.domain.CommentService
import com.d1t.dastargram.domain.comment.dto.CommentRequest
import org.springframework.stereotype.Service

@Service
class CommentFacade(val commentService: CommentService) {

    fun insert(insertRequest: CommentRequest.insertRequest) = commentService.insert(insertRequest)
    fun update(updateRequest: CommentRequest.updateRequest) = commentService.update(updateRequest)

    fun delete(deleteRequest: CommentRequest.deleteRequest) = commentService.delete(deleteRequest)
}
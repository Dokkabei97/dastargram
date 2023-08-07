package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse

interface CommentService {

    fun insert(insertRequest: CommentRequest.InsertRequest): CommentResponse.PublicResponse

    fun update(updateRequest: CommentRequest.UpdateRequest): CommentResponse.PublicResponse

    fun delete(deleteRequest: CommentRequest.DeleteRequest)
}
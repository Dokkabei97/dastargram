package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse

interface CommentService {

    fun insert(insertRequest: CommentRequest.insertRequest): CommentResponse

    fun update(updateRequest: CommentRequest.updateRequest): CommentResponse

    fun delete(deleteRequest: CommentRequest.deleteRequest)
}
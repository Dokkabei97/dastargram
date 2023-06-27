package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse

interface CommentService {

    fun createComment(createCommentRequest: CommentRequest.createCommentRequest): CommentResponse

    fun modifyComment(modifyCommentRequest: CommentRequest.modifyCommentRequest): CommentResponse

    fun removeComment(removeCommentRequest: CommentRequest.removeCommentRequest): CommentResponse
}
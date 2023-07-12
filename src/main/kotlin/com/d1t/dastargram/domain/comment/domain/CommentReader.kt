package com.d1t.dastargram.domain.comment.domain

interface CommentReader {
    fun getCommentById(commentId: Long): Comment
}
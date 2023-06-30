package com.d1t.dastargram.domain.comment.domain

interface CommentReader {
    fun findById(commentId: Long): Comment
}
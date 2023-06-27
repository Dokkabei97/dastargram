package com.d1t.dastargram.domain.comment.domain

interface CommentStore {
    fun create(comment: Comment) : Comment
    fun deleteById(commentId : Long)

    fun updateCommentById(commentId: Long)
}
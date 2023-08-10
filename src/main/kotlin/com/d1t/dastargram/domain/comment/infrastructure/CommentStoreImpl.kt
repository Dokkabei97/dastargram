package com.d1t.dastargram.domain.comment.infrastructure

import com.d1t.dastargram.domain.comment.domain.Comment
import com.d1t.dastargram.domain.comment.domain.CommentStore
import org.springframework.stereotype.Component

@Component
class CommentStoreImpl(val commentRepository: CommentRepository) : CommentStore {

    override fun create(comment: Comment) = commentRepository.save(comment)
    override fun deleteById(commentId: Long) = commentRepository.deleteById(commentId)

}
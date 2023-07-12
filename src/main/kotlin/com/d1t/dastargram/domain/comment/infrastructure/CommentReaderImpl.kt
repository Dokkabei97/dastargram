package com.d1t.dastargram.domain.comment.infrastructure

import com.d1t.dastargram.domain.comment.domain.Comment
import com.d1t.dastargram.domain.comment.domain.CommentReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentReaderImpl(val commentRepository: CommentRepository) : CommentReader {

    override fun getCommentById(commentId: Long): Comment = commentRepository.findByIdOrNull(commentId)
        ?: throw EntityNotFoundException("존재하지 않는 댓글입니다.")
}
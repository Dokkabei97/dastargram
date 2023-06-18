package com.d1t.dastargram.domain.comment.infrastructure

import com.d1t.dastargram.domain.comment.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}
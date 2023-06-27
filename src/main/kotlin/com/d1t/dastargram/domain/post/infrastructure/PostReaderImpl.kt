package com.d1t.dastargram.domain.post.infrastructure

import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.domain.post.domain.PostReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PostReaderImpl(val postRepository: PostRepository) : PostReader {

    override fun findById(memberId: Long): Post = postRepository.findByIdOrNull(memberId)
            ?: throw EntityNotFoundException("존재하지 않는 게시글입니다.")

}
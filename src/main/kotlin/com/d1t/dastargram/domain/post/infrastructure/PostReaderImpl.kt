package com.d1t.dastargram.domain.post.infrastructure

import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.domain.post.domain.PostReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PostReaderImpl(val postRepository: PostRepository) : PostReader {
    override fun findById(postId: Long): Post = postRepository.findByIdOrNull(postId)
        ?: throw EntityNotFoundException("존재하지 않는 게시물입니다.")

    override fun findByMemberId(memberId: Long): List<Post> = postRepository.findAllByMemberId(memberId)
        ?: emptyList()
}
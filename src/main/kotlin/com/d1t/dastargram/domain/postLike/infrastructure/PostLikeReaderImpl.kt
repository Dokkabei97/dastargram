package com.d1t.dastargram.domain.postLike.infrastructure

import com.d1t.dastargram.domain.postLike.domain.PostLike
import com.d1t.dastargram.domain.postLike.domain.PostLikeReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PostLikeReaderImpl(val postLikeRepository: PostLikeRepository): PostLikeReader {
    override fun getPostLikeById(id:Long): PostLike = postLikeRepository.findByIdOrNull(id)
        ?: throw EntityNotFoundException("존재하지 않는 좋아요")

}
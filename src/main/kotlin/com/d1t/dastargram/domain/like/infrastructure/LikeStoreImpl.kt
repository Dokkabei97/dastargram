package com.d1t.dastargram.domain.like.infrastructure

import com.d1t.dastargram.domain.like.domain.Like
import com.d1t.dastargram.domain.like.domain.LikeStore
import org.springframework.stereotype.Component

@Component
class LikeStoreImpl(val likeRepository: LikeRepository): LikeStore {
    override fun create(like: Like): Like = likeRepository.save(like)
    override fun deleteById(likeId: Long) = likeRepository.deleteById(likeId)
}
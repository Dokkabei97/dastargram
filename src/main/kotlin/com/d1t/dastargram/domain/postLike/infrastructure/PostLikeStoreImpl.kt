package com.d1t.dastargram.domain.postLike.infrastructure

import com.d1t.dastargram.domain.postLike.domain.PostLike
import com.d1t.dastargram.domain.postLike.domain.PostLikeStore
import org.springframework.stereotype.Component

@Component
class PostLikeStoreImpl(val postLikeRepository: PostLikeRepository): PostLikeStore {
    override fun create(postLike: PostLike): PostLike = postLikeRepository.save(postLike)
    override fun deleteById(likeId: Long) = postLikeRepository.deleteById(likeId)
}
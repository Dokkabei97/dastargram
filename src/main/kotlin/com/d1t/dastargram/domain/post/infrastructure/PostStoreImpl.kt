package com.d1t.dastargram.domain.post.infrastructure

import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.domain.post.domain.PostStore
import org.springframework.stereotype.Component

@Component
class PostStoreImpl(val postRepository: PostRepository) : PostStore {
    override fun create(post: Post) = postRepository.save(post)
    override fun deleteById(postId: Long) = postRepository.deleteById(postId);
}
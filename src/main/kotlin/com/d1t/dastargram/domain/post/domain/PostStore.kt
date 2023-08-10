package com.d1t.dastargram.domain.post.domain

interface PostStore {
    fun create(post: Post): Post
    fun deleteById(postId: Long)
}
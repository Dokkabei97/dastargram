package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.post.dto.PostRequest

interface PostService {
    fun upload(memberId: Long, postUploadRequest: PostRequest.PostUploadRequest): Post
}

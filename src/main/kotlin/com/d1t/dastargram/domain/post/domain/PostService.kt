package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*

interface PostService {
    fun upload(uploadPostRequest: UploadPostRequest): PostPublicResponse

    fun delete(deletePostRequest: DeletePostRequest)

    fun update(updatePostRequest: UpdatePostRequest): PostPublicResponse

    fun getPosts(memberId: Long): List<PostPublicResponse>
}
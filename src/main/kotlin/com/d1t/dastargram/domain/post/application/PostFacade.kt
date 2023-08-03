package com.d1t.dastargram.domain.post.application

import com.d1t.dastargram.domain.post.domain.PostService
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service

@Service
class PostFacade(
    val postService: PostService
) {
    fun upload(uploadPostRequest: UploadPostRequest): PostPublicResponse  = postService.upload(uploadPostRequest)

    fun delete(deletePostRequest: DeletePostRequest) = postService.delete(deletePostRequest)

    fun update(updatePostRequest: UpdatePostRequest): PostPublicResponse = postService.update(updatePostRequest)

    fun getPosts(memberId: Long): List<PostPublicResponse> = postService.getPosts(memberId)

}
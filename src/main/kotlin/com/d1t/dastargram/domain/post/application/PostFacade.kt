package com.d1t.dastargram.domain.post.application

import com.d1t.dastargram.domain.member.domain.MemberService
import com.d1t.dastargram.domain.post.domain.PostService
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostFacade(
    val postService: PostService
) {

    fun upload(uploadPostRequest: UploadPostRequest): PostPublicResponse  = postService.upload(uploadPostRequest)

    fun delete(deletePostRequest: DeletePostRequest) = postService.delete(deletePostRequest)

    fun update(updatePostRequest: UpdatePostRequest): PostPublicResponse = postService.update(updatePostRequest)

}
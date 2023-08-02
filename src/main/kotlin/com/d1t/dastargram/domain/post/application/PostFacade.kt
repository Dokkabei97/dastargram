package com.d1t.dastargram.domain.post.application

import com.d1t.dastargram.domain.member.domain.MemberService
import com.d1t.dastargram.domain.post.domain.PostService
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostFacade(
    val postService: PostService,
    val memberService: MemberService
) {
    @Transactional
    fun upload(postUploadRequest: UploadPostRequest): PostPublicResponse {
        //1. find member
        val member = memberService.findById(postUploadRequest.memberId)

        //2. post create
        val post = postService.upload(
            member,
            postUploadRequest.content,
            postUploadRequest.postImages
        )

        return PostPublicResponse(
            post.id,
            post.content,
            post.likeCount,
            post.postImages
        )
    }

    fun delete(deletePostRequest: DeletePostRequest) = postService.delete(deletePostRequest)

    fun update(updatePostRequest: UpdatePostRequest) = postService.update(updatePostRequest)

}
package com.d1t.dastargram.domain.post.application

import com.d1t.dastargram.domain.member.domain.MemberService
import com.d1t.dastargram.domain.member.dto.MemberRequest
import com.d1t.dastargram.domain.post.domain.PostService
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostFacade(val postService: PostService, val memberService: MemberService) {
    @Transactional
    fun upload(memberId: Long, postUploadRequest: UploadPostRequest): PostPublicResponse {
        //1. find member
        val member = memberService.findById(memberId)

        //2. post create
        val post = postService.upload(
            member,
            postUploadRequest
        )

        return PostPublicResponse(
            post.id,
            post.content,
            post.likeCount,
            post.postImages
        )
    }

    fun delete(memberId: Long, deletePostRequest: DeletePostRequest) = postService.delete(memberId, deletePostRequest)

    fun update(memberId: Long, updatePostRequest: UpdatePostRequest) = postService.update(memberId, updatePostRequest)

}

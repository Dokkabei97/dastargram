package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*

interface PostService {
    fun upload(member: Member, postUploadRequest: UploadPostRequest): Post

    fun delete(memberId: Long, deletePostRequest: DeletePostRequest)

    fun update(memberId: Long, updatePostRequest: UpdatePostRequest): PostPublicResponse
}

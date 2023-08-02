package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*

interface PostService {
    fun upload(member: Member, content: String?, postImages: List<String>): Post

    fun delete(deletePostRequest: DeletePostRequest)

    fun update(updatePostRequest: UpdatePostRequest): PostPublicResponse
}
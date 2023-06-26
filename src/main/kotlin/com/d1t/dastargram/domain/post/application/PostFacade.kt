package com.d1t.dastargram.domain.post.application

import com.d1t.dastargram.domain.post.domain.PostService
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import org.springframework.stereotype.Service

@Service
class PostFacade(val postService: PostService) {
    fun upload(memberId: Long, postUploadRequest: PostUploadRequest) = postService.upload(memberId, postUploadRequest)

    fun delete(memberId: Long, postUploadRequest: PostUploadRequest) = postService.delete(memberId, postUploadRequest)

    fun update(memberId: Long, postUploadRequest: PostUploadRequest) = postService.update(memberId, postUploadRequest)
}

package com.d1t.dastargram.domain.postLike.application

import com.d1t.dastargram.domain.postLike.domain.PostLikeService
import com.d1t.dastargram.domain.postLike.dto.PostLikeRequest
import org.springframework.stereotype.Service

@Service
class PostLikeFacade (val postLikeService: PostLikeService) {
    fun insert(insertRequest: PostLikeRequest.InsertRequest) = postLikeService.insert(insertRequest)
    fun delete(deleteRequest: PostLikeRequest.DeleteRequest) = postLikeService.delete(deleteRequest)
}
package com.d1t.dastargram.domain.postLike.domain

import com.d1t.dastargram.domain.postLike.dto.PostLikeRequest
import com.d1t.dastargram.domain.postLike.dto.PostLikeResponse

interface PostLikeService {
    fun insert(insertRequest: PostLikeRequest.InsertRequestPost) : PostLikeResponse
}
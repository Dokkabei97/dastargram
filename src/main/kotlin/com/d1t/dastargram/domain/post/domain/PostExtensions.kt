package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.post.dto.PostResponse.*

fun Post.toPostPublicResponse() = PostPublicResponse(
    id = id!!,
    content = content,
    likeCount = likeCount,
    postImages = postImages
)
package com.d1t.dastargram.domain.bookmark.dto

import com.d1t.dastargram.domain.post.domain.Post

data class BookmarkResponse(
    val bookmarkId: Long,
    val postId : Long
)
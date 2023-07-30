package com.d1t.dastargram.domain.bookmark.dto

sealed class BookmarkRequest (
    open val postId: Long,
    open val memberId: Long
) {

    data class SelectRequest(
        override val postId: Long,
        override val memberId: Long
    ): BookmarkRequest(
        postId = postId,
        memberId = memberId
    )
    data class InsertRequest(
        override val postId: Long,
        override val memberId: Long
    ) : BookmarkRequest(
        postId = postId,
        memberId = memberId
    )

    data class UpdateRequest(
        override val postId: Long,
        override val memberId: Long,
        val bookmarkId : Long
    ) : BookmarkRequest(
        postId = postId,
        memberId = memberId
    )

    data class DeleteRequest(
        val memberId: Long,
        val bookmarkId : Long
    )
}
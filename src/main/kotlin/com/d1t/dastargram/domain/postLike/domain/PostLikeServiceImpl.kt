package com.d1t.dastargram.domain.postLike.domain

import com.d1t.dastargram.domain.postLike.dto.PostLikeRequest
import com.d1t.dastargram.domain.postLike.dto.PostLikeResponse
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostLikeServiceImpl (
    val memberReader: MemberReader,
    val postReader: PostReader,
    val postLikeStore: PostLikeStore,
    val postLikeReader: PostLikeReader
) : PostLikeService {
    override fun insert(insertRequest: PostLikeRequest.InsertRequest): PostLikeResponse {
        val member = memberReader.getMemberById(insertRequest.memberId)
        val post = postReader.findById(insertRequest.postId)
        val postLike = postLikeStore.create(
            PostLike.create(
                member,
                post
            )
        )
        return PostLikeResponse(
            postLike.id!!,
            post.id!!
        )
    }

    @Transactional
    override fun delete(deleteRequest: PostLikeRequest.DeleteRequest) {
        val postLike = postLikeReader.getPostLikeById(deleteRequest.postLikeId)
        postLikeStore.deleteById(deleteRequest.postLikeId)
    }
}
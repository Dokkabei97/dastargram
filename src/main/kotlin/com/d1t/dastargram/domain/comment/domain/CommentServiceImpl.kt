package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentServiceImpl(val memberReader: MemberReader, val postReader: PostReader, val commentStore: CommentStore) : CommentService {

    @Transactional
    override fun createComment(createCommentRequest: CommentRequest.createCommentRequest): CommentResponse {

        val member = memberReader.findById(createCommentRequest.memberId)
        val post = postReader.findById(createCommentRequest.postId)

        val comment = commentStore.create(
                Comment.create(
                    createCommentRequest.content,
                        member,
                        post
                )
        )

        return CommentResponse(
            post.id,
            member.id,
            comment.content
        )
    }

    @Transactional
    override fun modifyComment(modifyCommentRequest: CommentRequest.modifyCommentRequest): CommentResponse {
        val member = memberReader.findById(modifyCommentRequest.memberId)

        commentStore.updateCommentById(modifyCommentRequest.id)
        return CommentResponse(
            0,
            0,
            "test"
        )
    }

    @Transactional
    override fun removeComment(removeCommentRequest: CommentRequest.removeCommentRequest): CommentResponse{
        return CommentResponse(
            0,
            0,
            "test"
        )
    }
}